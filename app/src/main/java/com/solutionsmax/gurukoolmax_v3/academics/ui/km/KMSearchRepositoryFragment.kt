package com.solutionsmax.gurukoolmax_v3.academics.ui.km

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
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PopulateSubjectProgramsListParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectProgramList
import com.solutionsmax.gurukoolmax_v3.academics.ui.AcademicsViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.SubjectManagementViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.spinner_adapter.search.KMS_Board
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.spinner_adapter.search.KMS_Class
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.spinner_adapter.search.KMS_ContentType
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.spinner_adapter.search.KMS_Subject
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_MASTER_LIST
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
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentKMSearchRepositoryBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class KMSearchRepositoryFragment : BaseFragment() {
    private lateinit var binding: FragmentKMSearchRepositoryBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var academicsViewModel: AcademicsViewModel
    private lateinit var subjectManagementViewModel: SubjectManagementViewModel

    companion object {
        var cboBoard: TextView? = null
        var iAcademicBoardID = -1
        var cboClass: TextView? = null
        var iClassID = -1
        var cboSubject: TextView? = null
        var iSubjectID = -1
        var cboContentType: TextView? = null
        var iContentTypeID = -1
        var dialog: Dialog? = null
    }

    private lateinit var boardItems: List<PopulateMasterListItem>
    private lateinit var classItems: List<PopulateClassItems>
    private lateinit var populateSubjectList: List<PopulateSubjectProgramList>
    private lateinit var contentTypesItems: List<PopulateMasterListItem>

    private var sClassMethod = ""
    private var iSettingsID = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentKMSearchRepositoryBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            title = getString(R.string.knowledge_management)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

            setNavigationOnClickListener {
                val bundle = bundleOf("menu" to OperationMenuConstants.ACADEMICS)
                currentNavController.navigate(
                    R.id.administratorSubMenuFragment, bundle
                )
            }
        }

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]
        mastersViewModel =
            ViewModelProvider(this, viewModelFactory)[MastersViewModel::class.java]
        settingsViewModel = ViewModelProvider(this, viewModelFactory)[SettingsViewModel::class.java]
        academicsViewModel =
            ViewModelProvider(this, viewModelFactory)[AcademicsViewModel::class.java]
        subjectManagementViewModel =
            ViewModelProvider(this, viewModelFactory)[SubjectManagementViewModel::class.java]

        cboBoard = view.findViewById(R.id.cboBoard)
        cboClass = view.findViewById(R.id.cboClass)
        cboSubject = view.findViewById(R.id.cboSubject)
        cboContentType = view.findViewById(R.id.cboContentType)

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        settingsViewModel.retrieveSettings()
        setUpObservers()

        cboBoard!!.setOnClickListener {
            showBoardDialog()
        }

        cboContentType!!.setOnClickListener {
            showContentTypeDialog()
        }

        cboClass!!.setOnClickListener {
            if (::classItems.isInitialized) {
                showClassDialog()
            }
        }

        cboSubject!!.setOnClickListener {
            if (::populateSubjectList.isInitialized) {
                showSubjectDialog()
            }
        }

        binding.btnSubmit.setOnClickListener {
            if (iAcademicBoardID == -1 || iClassID == -1 || iSubjectID == -1 || iContentTypeID == -1 ||
                TextUtils.isEmpty(binding.txtSearchKeyword.text.toString())
            ) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                val data = KMSearchBundle(
                    iBoardID = iAcademicBoardID,
                    iClassID = iClassID,
                    iSubjectID = iSubjectID,
                    iContentTypeID = iContentTypeID,
                    sSearchKey = binding.txtSearchKeyword.text.toString()
                )
                val bundle = bundleOf("data" to data)
                currentNavController.navigate(R.id.kmSearchResultListFragment, bundle)
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
                populateContentTypes(sBaseURL, sToken)
            }
        }
    }

    /**
     * Academic Board
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
            adapter = KMS_Board(
                boardItems,
                KMS_Board.OnItemClick {
                    cboBoard!!.text = it.sName
                    iAcademicBoardID = it.id
                    if (iAcademicBoardID > 0) {
                        if (iSettingsID == 1) {
                            populateClass(iAcademicBoardID)
                        } else {
                            populateSemesterClass(iAcademicBoardID)
                        }
                    }
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
                adapter = KMS_Class(
                    classItems,
                    KMS_Class.OnItemClick {
                        cboClass!!.text = it.sClassStandard
                        iClassID = it.iClassStandardID
                        if (iAcademicBoardID > 0) {
                            populateSubject()
                        }
                    })
            }
            dialog!!.show()

            val dialogClose: ImageView =
                dialog!!.findViewById(R.id.imgClose)
            dialogClose.setOnClickListener { dialog!!.cancel() }
        }
    }

    /**
     * Populate Subject
     */
    private fun populateSubject() {
        subjectManagementViewModel.populateSubjectProgramList(
            PopulateSubjectProgramsListParams(
                url = sBaseURL,
                sAuthorization = sToken,
                iGroupID = 1,
                iSchoolID = 1,
                iBoardID = iAcademicBoardID,
                iStatusID = -1
            )
        )
        subjectManagementViewModel.mutablePopulateSubjectProgramList.observe(viewLifecycleOwner) {
            populateSubjectList = it
        }
    }

    private fun showSubjectDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.subject)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = KMS_Subject(
                populateSubjectList,
                KMS_Subject.OnItemClick {
                    cboSubject!!.text = it.sSubjectName
                    iSubjectID = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }

    }

    /**
     * Populate Content type
     */
    private fun populateContentTypes(sBaseURL: String, sToken: String) {
        mastersViewModel.populateKMContentType(
            url = sBaseURL + POPULATE_MASTER_LIST,
            sAuthorization = sToken,
            sTableName = MasterTableNames.MASTERS_KNOWLEDGE_MANAGEMENT_CONTENT_TYPES
        )
        mastersViewModel.populateKmContentTypeMutableData.observe(viewLifecycleOwner) {
            contentTypesItems = it
        }
    }

    private fun showContentTypeDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.content_type)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = KMS_ContentType(
                contentTypesItems,
                KMS_ContentType.OnItemClick {
                    cboBoard!!.text = it.sName
                    iAcademicBoardID = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
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
                sErrorSource = KMSearchRepositoryFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }
}