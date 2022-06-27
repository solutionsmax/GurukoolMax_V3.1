package com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common.PopulateClassItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PopulateClassParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PopulateSemesterClassParams
import com.solutionsmax.gurukoolmax_v3.academics.ui.AcademicsViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project.spinner_adapter.PRSApprovalStatus
import com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project.spinner_adapter.PRSBoard
import com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project.spinner_adapter.PRSClass
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_PROJECTS_REPOSITORY_WORKFLOW_STATUS
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.PortalIdConstants
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Event
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.ErrorLogsViewModel
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.SettingsViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentProjectsRepositorySearchBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class ProjectsRepositorySearchFragment : BaseFragment() {
    private lateinit var binding: FragmentProjectsRepositorySearchBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var academicsViewModel: AcademicsViewModel

    companion object {
        var cboBoard: TextView? = null
        var iAcademicBoard: Int = -1
        var cboClass: TextView? = null
        var iClassID: Int = -1
        var cboApprovalStatus: TextView? = null
        var iApprovalStatusID = -1
        var dialog: Dialog? = null
    }

    private lateinit var boardItems: List<PopulateMasterListItem>
    private lateinit var classItems: List<PopulateClassItems>
    private lateinit var approvalStatusList: List<PopulateMasterListItem>

    private var sClassMethod = ""
    private var iSettingsID = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProjectsRepositorySearchBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            title = getString(R.string.search_project_repo)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            val bundle = bundleOf("menu" to OperationMenuConstants.ACADEMICS)
            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.administratorSubMenuFragment,
                    bundle
                )
            }
        }

        cboBoard = view.findViewById(R.id.cboBoard)
        cboClass = view.findViewById(R.id.cboClass)
        cboApprovalStatus = view.findViewById(R.id.cboApprovalStatus)

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]
        mastersViewModel = ViewModelProvider(this, viewModelFactory)[MastersViewModel::class.java]
        settingsViewModel = ViewModelProvider(this, viewModelFactory)[SettingsViewModel::class.java]
        academicsViewModel =
            ViewModelProvider(this, viewModelFactory)[AcademicsViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        settingsViewModel.retrieveSettings()
        setUpObservers()

        cboBoard!!.setOnClickListener {
            showBoardDialog()
        }

        cboApprovalStatus!!.setOnClickListener {
            showApprovalStatusDialog()
        }

        cboClass!!.setOnClickListener {
            if (iAcademicBoard > 0) {
                if (iSettingsID == 1) {
                    populateClass(iAcademicBoard)
                } else {
                    populateSemesterClass(iAcademicBoard)
                }
                if (::classItems.isInitialized) {
                    showClassDialog()
                }
            }
        }

        binding.btnSubmit.setOnClickListener {
            if (iAcademicBoard == -1 || iClassID == -1 || iApprovalStatusID == -1 ||
                TextUtils.isEmpty(binding.txtProjectName.text.toString()) ||
                TextUtils.isEmpty(binding.txtTechInvolved.text.toString()) ||
                TextUtils.isEmpty(binding.txtSearchKeyword.text.toString())
            ) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                val data = ProjectRepoSearchBundle(
                    iBoardID = iAcademicBoard,
                    iClassID = iClassID,
                    sProjectName = binding.txtProjectName.text.toString(),
                    sTechnologyInvolved = binding.txtTechInvolved.text.toString(),
                    sSearch = binding.txtSearchKeyword.text.toString()
                )
                val bundle = bundleOf("data" to data)
                currentNavController.navigate(R.id.projectRepositorySearchResultFragment, bundle)
            }
        }
    }

    private fun setUpObservers() {
        with(mastersViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(error = it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
            }
            settingsViewModel.retrieveSettingsLiveData.observe(viewLifecycleOwner) {
                for (items in it) {
                    iSettingsID = items.setting_id
                    sClassMethod = if (items.setting_id == 1) {
                        MethodConstants.POPULATE_CLASS_SEMESTER_FORMAT_1
                    } else {
                        MethodConstants.POPULATE_CLASS_SEMESTER_FORMAT_2
                    }
                }
            }
            tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
                sBaseURL = it.sBaseURL
                sToken = it.sToken

                populateAcademicBoard(sBaseURL, sToken)
                populateCurrentApprovalStatus(sBaseURL, sToken)
            }
        }
    }

    /**
     * Populate Approval Status
     */
    private fun populateCurrentApprovalStatus(sBaseURL: String, sToken: String) {
        mastersViewModel.populateApprovalStatus(
            url = sBaseURL,
            sAuthorization = sToken,
            sTableName = MASTERS_PROJECTS_REPOSITORY_WORKFLOW_STATUS
        )
        mastersViewModel.populateApprovalStatusMutableData.observe(viewLifecycleOwner) {
            approvalStatusList = it
        }
    }

    private fun showApprovalStatusDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.current_approval_status)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PRSApprovalStatus(
                approvalStatusList,
                PRSApprovalStatus.OnItemClick {
                    cboApprovalStatus!!.text = it.sName
                    iApprovalStatusID = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    /**
     * Populate Board
     */
    private fun populateAcademicBoard(sBaseURL: String, sToken: String) {
        mastersViewModel.populateBoard(
            sBaseURL, sToken,
            MasterTableNames.MASTERS_ACADEMICS_EDUCATIONAL_BOARD
        )

        mastersViewModel.populateBoardMutableData.observe(viewLifecycleOwner) {
            boardItems = it
        }
    }

    private fun showBoardDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.academic_board)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PRSBoard(
                boardItems,
                PRSBoard.OnItemClick {
                    cboBoard!!.text = it.sName
                    iAcademicBoard = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    /**
     * Populate Class
     */
    private fun populateSemesterClass(iBoardID: Int) {
        academicsViewModel.populateClassSemester(
            PopulateSemesterClassParams(
                url = sBaseURL + sClassMethod,
                sAuthorization = sToken,
                iGroupID = 1,
                iSchoolID = 1,
                iBoardID = iBoardID,
                iStatusID = 4
            )
        )
        academicsViewModel.mutablePopulateSemesterClass.observe(viewLifecycleOwner) {
            classItems = it
        }
    }

    private fun populateClass(iBoardID: Int) {
        academicsViewModel.populateClass(
            PopulateClassParams(
                url = sBaseURL + sClassMethod,
                sAuthorization = sToken,
                iGroupID = 1,
                iSchoolID = 1,
                iBoardID = iBoardID,
                iYearId = -1,
                iStatusID = -1
            )
        )
        academicsViewModel.mutablePopulateClass.observe(viewLifecycleOwner) {
            classItems = it
        }
    }

    private fun showClassDialog() {
        if (classItems.isNotEmpty()) {
            dialog = Dialog(requireContext())
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setCancelable(true)
            dialog!!.setContentView(R.layout.spinner_dialog)

            val dialogHeader: TextView =
                dialog!!.findViewById(R.id.lblDialogHeading)
            dialogHeader.text = getString(R.string.class_standard)

            val dialogRecyclerView: RecyclerView =
                dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
            dialogRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = PRSClass(
                    classItems,
                    PRSClass.OnItemClick {
                        cboClass!!.text = it.sClassStandard
                        iClassID = it.iClassStandardID
                    })
            }
            dialog!!.show()

            val dialogClose: ImageView =
                dialog!!.findViewById(R.id.imgClose)
            dialogClose.setOnClickListener { dialog!!.cancel() }
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun postErrors(event: Event<Failure>) {
        errorLogsViewModel.postErrorLogs(
            url = sBaseURL + MethodConstants.POST_ERROR_LOGS,
            sAuthorization = sToken,
            postErrorLogsItems = PostErrorLogsItems(
                iGroupID = iGroupID,
                iPlantID = iBranchID,
                iUserRegistrationID = 1,
                iPortalID = PortalIdConstants.MANAGEMENT_PORTAL,
                sErrorException = event.peekContent().stackTraceToString(),
                sErrorMessage = event.peekContent().localizedMessage,
                sErrorTrace = event.peekContent().message.toString(),
                iReviewStatusID = -1,
                sErrorSource = ProjectsRepositorySearchFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

}