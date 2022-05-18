package com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.grade_system

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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.cce_scholastic.PostCceScholasticItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common.PopulateClassItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.ui.AcademicsViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.CceScholasticViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.grade_system.spinner_adapter.SGS_BoardAdapter
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.grade_system.spinner_adapter.SGS_ClassAdapter
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_ACADEMICS_EDUCATIONAL_BOARD
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_CLASS_SEMESTER_FORMAT_1
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_CLASS_SEMESTER_FORMAT_2
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
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentScholasticGradeSystemInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class ScholasticGradeSystemInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentScholasticGradeSystemInfoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var cceScholasticViewModel: CceScholasticViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var academicsViewModel: AcademicsViewModel
    private lateinit var settingsViewModel: SettingsViewModel
    private var sClassMethod = ""
    private var iSettingsID = -1


    companion object {
        var lblBoard: TextView? = null
        var lblClass: TextView? = null
        var iBoardID: Int = -1
        var iClassID: Int = -1
        var dialog: Dialog? = null
    }

    private var iEditID = -1

    private lateinit var boardItems: List<PopulateMasterListItem>
    private lateinit var classItems: List<PopulateClassItems>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentScholasticGradeSystemInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iEditID = requireArguments().getInt("id", -1)

        lblBoard = view.findViewById(R.id.cboBoard)
        lblClass = view.findViewById(R.id.cboClass)

        binding.toolbar.apply {
            title = getString(R.string.cce_scholastic_grade_system_info)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.scholasticGradeSystemListFragment
                )
            }
        }

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]
        cceScholasticViewModel =
            ViewModelProvider(this, viewModelFactory)[CceScholasticViewModel::class.java]
        mastersViewModel = ViewModelProvider(this, viewModelFactory)[MastersViewModel::class.java]
        academicsViewModel =
            ViewModelProvider(this, viewModelFactory)[AcademicsViewModel::class.java]
        settingsViewModel = ViewModelProvider(this, viewModelFactory)[SettingsViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        settingsViewModel.retrieveSettings()
        setUpObservers()

        lblBoard!!.setOnClickListener {
            showBoardDialog()
        }

        lblClass!!.setOnClickListener {
            if (iBoardID > 0) {
                if (iSettingsID == 1) {
                    populateClass(iBoardID)
                } else {
                    populateSemesterClass(iBoardID)
                }
                if (::classItems.isInitialized) {
                    showClassDialog()
                }
            }
        }

        binding.btnSubmit.setOnClickListener {
            if (iBoardID < 0 || iClassID < 0 || TextUtils.isEmpty(binding.txtGrade.text) || TextUtils.isEmpty(
                    binding.txtMAxScoreRange.text
                ) || TextUtils.isEmpty(binding.txtMinScoreRange.text) || TextUtils.isEmpty(binding.txtRangePoint.text)
            ) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                binding.progressBar.visibility = View.VISIBLE
                cceScholasticViewModel.checkDuplicateScholasticGradingAllInfo(
                    CheckDuplicateScholasticGradingAll(
                        url = sBaseURL,
                        sAuthorization = sToken,
                        iGroupID = 1,
                        iSchoolID = 1,
                        iBoardID = iBoardID,
                        iClassID = iClassID,
                        iMinScore = binding.txtMinScoreRange.text.toString().toInt(),
                        iMaxScore = binding.txtMAxScoreRange.text.toString().toInt(),
                        sGrade = binding.txtGrade.text.toString(),
                        dGradePoint = binding.txtRangePoint.text.toString().toDouble()
                    )
                )
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
                        POPULATE_CLASS_SEMESTER_FORMAT_1
                    } else {
                        POPULATE_CLASS_SEMESTER_FORMAT_2
                    }
                }
            }
            tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
                sBaseURL = it.sBaseURL
                sToken = it.sToken

                populateAcademicBoard(sBaseURL, sToken)

                if (iEditID > 0) {
                    retrieveDetails(iEditID)
                }
            }
        }

        with(cceScholasticViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(error = it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
            }
            mutableRetrieveDetailsScholasticGrading.observe(viewLifecycleOwner) {
                for (items in it) {
                    binding.cboBoard.text = items.sAcademicBoard
                    iBoardID = items.iAcademicBoardID
                    binding.cboClass.text = items.sClassGrade
                    iClassID = items.iClassGradeID
                    binding.txtMinScoreRange.setText(items.iMinScoreRange)
                    binding.txtMAxScoreRange.setText(items.iMaxScoreRange)
                    binding.txtGrade.setText(items.sGrade)
                    binding.txtRangePoint.setText(items.sMaxRange)
                }
            }

            mutableCheckDuplicateScholasticGradingAll.observe(viewLifecycleOwner) { duplicate ->
                if (iEditID > 0) {
                    if (duplicate > 0) {
                        if (duplicate == iEditID) {
                            amendInfo()
                        } else {
                            binding.progressBar.visibility = View.INVISIBLE
                            showError(
                                getString(R.string.duplicate_info),
                                getString(R.string.duplicate_info_desc)
                            )
                        }
                    } else {
                        amendInfo()
                    }
                } else {
                    if (duplicate > 0) {
                        binding.progressBar.visibility = View.INVISIBLE
                        showError(
                            getString(R.string.duplicate_info),
                            getString(R.string.duplicate_info_desc)
                        )
                    } else {
                        postInfo()
                    }
                }
            }

            mutablePostCceScholasticMutableDate.observe(viewLifecycleOwner) {
                if (it > 0) {
                    currentNavController.navigate(R.id.scholasticGradeSystemListFragment)
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                    showError(
                        getString(R.string.could_not_save_info),
                        getString(R.string.could_not_save_info_desc)
                    )
                }
            }

            mutableAmendCceScholasticMutableDate.observe(viewLifecycleOwner) {
                if (it > 0) {
                    currentNavController.navigate(R.id.scholasticGradeSystemListFragment)
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                    showError(
                        getString(R.string.could_not_save_info),
                        getString(R.string.could_not_save_info_desc)
                    )
                }
            }
        }

    }

    private fun postInfo() {
        cceScholasticViewModel.postCceScholasticInfo(
            PostCceScholasticParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postCceScholasticItem = PostCceScholasticItem(
                    iGroupID = 1,
                    iSchoolID = 1,
                    iAcademicBoardID = iBoardID,
                    iClassGradeID = iClassID,
                    sMarkRange = binding.txtRangePoint.text.toString(),
                    iMinScoreRange = binding.txtMinScoreRange.text.toString().toInt(),
                    iMaxScoreRange = binding.txtMAxScoreRange.text.toString().toInt(),
                    sGrade = binding.txtGrade.text.toString(),
                    iGradePoint = binding.txtRangePoint.text.toString().toInt(),
                    iWorkflowStatusID = 1,
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    private fun amendInfo() {
        cceScholasticViewModel.amendCceScholasticInfo(
            PostCceScholasticParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postCceScholasticItem = PostCceScholasticItem(
                    id = iEditID,
                    iGroupID = 1,
                    iSchoolID = 1,
                    iAcademicBoardID = iBoardID,
                    iClassGradeID = iClassID,
                    sMarkRange = binding.txtRangePoint.text.toString(),
                    iMinScoreRange = binding.txtMinScoreRange.text.toString().toInt(),
                    iMaxScoreRange = binding.txtMAxScoreRange.text.toString().toInt(),
                    sGrade = binding.txtGrade.text.toString(),
                    iGradePoint = binding.txtRangePoint.text.toString().toInt(),
                    iWorkflowStatusID = 1,
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    private fun retrieveDetails(iEditID: Int) {
        cceScholasticViewModel.retrieveDetailsScholasticGrading(
            IdScholasticGrading(
                url = sBaseURL,
                sAuthorization = sToken,
                id = iEditID
            )
        )
    }

    /**
     * Populate Board
     */
    private fun populateAcademicBoard(sBaseURL: String, sToken: String) {
        mastersViewModel.populateBoard(sBaseURL, sToken, MASTERS_ACADEMICS_EDUCATIONAL_BOARD)

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
            adapter = SGS_BoardAdapter(
                boardItems,
                SGS_BoardAdapter.OnItemClick {
                    lblBoard!!.text = it.sName
                    iBoardID = it.id
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
                iStatusID = -1
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

            val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
            dialogHeader.text = getString(R.string.class_standard)

            val dialogRecyclerView: RecyclerView =
                dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
            dialogRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = SGS_ClassAdapter(
                    classItems,
                    SGS_ClassAdapter.OnItemClick {
                        lblClass!!.text = it.sClassStandard
                        iClassID = it.iClassStandardID
                    })
            }
            dialog!!.show()

            val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
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
                sErrorSource = ScholasticGradeSystemInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

}