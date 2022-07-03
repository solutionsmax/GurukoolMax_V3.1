package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.subject_management

import android.app.Dialog
import android.os.Build
import android.os.Bundle
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
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common.PopulateClassItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PostSubjectManagementInfo
import com.solutionsmax.gurukoolmax_v3.academics.ui.AcademicsViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.SubjectManagementViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.subject_management.spinner_adapter.SM_AcademicBoard
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.subject_management.spinner_adapter.SM_AcademicYear
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.subject_management.spinner_adapter.SM_Class
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.subject_management.spinner_adapter.SM_Subject
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
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentSubjectManagementInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class SubjectManagementInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentSubjectManagementInfoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var academicsViewModel: AcademicsViewModel
    private lateinit var subjectManagementViewModel: SubjectManagementViewModel

    companion object {
        var cboAcademicYear: TextView? = null
        var iAcademicYearID: Int = -1
        var cboAcademicBoard: TextView? = null
        var iAcademicBoard: Int = -1
        var cboClass: TextView? = null
        var iClassID: Int = -1
        var cboSubject: TextView? = null
        var iSubjectID: Int = -1
        var dialog: Dialog? = null
    }

    private var boardItems: List<PopulateMasterListItem> = emptyList()
    private var classItems: List<PopulateClassItems> = emptyList()
    private var academicYearList: List<PopulateMasterListItem> = emptyList()
    private var populateSubjectList: List<PopulateSubjectList> = emptyList()

    private var iEditID = -1
    private var sClassMethod = ""
    private var iSettingsID = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSubjectManagementInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cboAcademicYear = view.findViewById(R.id.cboAcademicYear)
        cboAcademicBoard = view.findViewById(R.id.cboAcademicBoard)
        cboClass = view.findViewById(R.id.cboClass)
        cboSubject = view.findViewById(R.id.cboSubject)

        iEditID = requireArguments().getInt("id", -1)

        binding.toolbar.apply {
            title = getString(R.string.link_class_sections_with_subject)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.subjectManagementListFragment
                )
            }
        }

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]
        mastersViewModel = ViewModelProvider(this, viewModelFactory)[MastersViewModel::class.java]
        settingsViewModel = ViewModelProvider(this, viewModelFactory)[SettingsViewModel::class.java]
        academicsViewModel =
            ViewModelProvider(this, viewModelFactory)[AcademicsViewModel::class.java]
        subjectManagementViewModel =
            ViewModelProvider(this, viewModelFactory)[SubjectManagementViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        settingsViewModel.retrieveSettings()
        setUpObservers()

        cboAcademicBoard!!.setOnClickListener {
            showBoardDialog()
        }

        cboAcademicYear!!.setOnClickListener {
            showAcademicYearDialog()
        }

        cboClass!!.setOnClickListener {
            showClassDialog()
        }

        cboSubject!!.setOnClickListener {
            showSubjectDialog()
        }

        binding.btnSubmit.setOnClickListener {
            if (iAcademicYearID == -1 || iAcademicBoard == -1 || iClassID == -1 || iSubjectID == -1) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                binding.progressBar.visibility = View.VISIBLE
                subjectManagementViewModel.checkDuplicateSubjectInfo(
                    CheckDuplicateSubjectInfoParams(
                        url = sBaseURL,
                        sAuthorization = sToken,
                        iGroupID = 1,
                        iSchoolID = 1,
                        iBoardID = iAcademicBoard,
                        iClassID = iClassID,
                        iSubjectID = iSubjectID,
                        iYearID = iAcademicYearID
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
                        MethodConstants.POPULATE_CLASS_SEMESTER_FORMAT_1
                    } else {
                        MethodConstants.POPULATE_CLASS_SEMESTER_FORMAT_2
                    }
                }
            }
            tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
                sBaseURL = it.sBaseURL
                sToken = it.sToken

                populateAcademicYear(sBaseURL, sToken)
                populateAcademicBoard(sBaseURL, sToken)

                if (iEditID > 0) {
                    retrieveDetails(iEditID)
                }
            }
            with(subjectManagementViewModel) {
                errorLiveData.observe(viewLifecycleOwner) {
                    showError(error = it.peekContent())
                    with(errorLogsViewModel) {
                        postErrors(it)
                        postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                    }
                }
                checkDuplicateMutableSubjectInfo.observe(viewLifecycleOwner) { duplicate ->
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

                postMutableSubjectInfo.observe(viewLifecycleOwner) { postInfo ->
                    if (postInfo > 0) {
                        currentNavController.navigate(R.id.subjectManagementListFragment)
                    } else {
                        binding.progressBar.visibility = View.INVISIBLE
                        showError(
                            getString(R.string.could_not_save_info),
                            getString(R.string.could_not_save_info_desc)
                        )
                    }
                }

                amendMutableSubjectInfo.observe(viewLifecycleOwner) { amendInfo ->
                    if (amendInfo > 0) {
                        currentNavController.navigate(R.id.subjectManagementListFragment)
                    } else {
                        binding.progressBar.visibility = View.INVISIBLE
                        showError(
                            getString(R.string.could_not_save_info),
                            getString(R.string.could_not_save_info_desc)
                        )
                    }
                }

                retrieveMutableSubjectDetails.observe(viewLifecycleOwner) { details ->
                    for (items in details) {
                        cboAcademicBoard!!.text = items.sAcademicBoard
                        iAcademicBoard = items.iAcademicBoardID
                        cboClass!!.text = items.sClassStandard
                        iClassID = items.iClassStandardID
                        cboAcademicYear!!.text = items.sCalendarYear
                        iAcademicYearID = items.iCalendarYearID
                        cboSubject!!.text = items.sSubjectName
                        iSubjectID = items.iSubjectID
                        binding.txtRemarks.setText(items.sRemarks)
                        populateAcademicYear(sBaseURL, sToken)
                        populateAcademicBoard(sBaseURL, sToken)
                    }
                }
            }
        }
    }

    /**
     * Retrieve Details
     */
    private fun retrieveDetails(iEditID: Int) {
        subjectManagementViewModel.retrieveSubjectDetails(
            RetrieveSubjectDetailsParams(
                url = sBaseURL,
                sAuthorization = sToken,
                id = iEditID
            )
        )
    }

    /**
     * Post Info
     */
    private fun postInfo() {
        subjectManagementViewModel.postSubjectInfo(
            PostSubjectInfoParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postSubjectManagementInfo = PostSubjectManagementInfo(
                    iGroupID = 1,
                    iSchoolID = 1,
                    iSubjectID = iSubjectID,
                    iAcademicBoardID = iAcademicBoard,
                    iClassStandardID = iClassID,
                    iCalendarYearID = iAcademicYearID,
                    sRemarks = binding.txtRemarks.text.toString(),
                    iUserID = 1,
                    iWorkflowStatusID = 1,
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    /**
     * Amend Info
     */
    private fun amendInfo() {
        subjectManagementViewModel.amendSubjectInfo(
            PostSubjectInfoParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postSubjectManagementInfo = PostSubjectManagementInfo(
                    id = iEditID,
                    iGroupID = 1,
                    iSchoolID = 1,
                    iSubjectID = iSubjectID,
                    iAcademicBoardID = iAcademicBoard,
                    iClassStandardID = iClassID,
                    iCalendarYearID = iAcademicYearID,
                    sRemarks = binding.txtRemarks.text.toString(),
                    iUserID = 1,
                    iWorkflowStatusID = 1,
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    /**
     * Academic Year
     */
    private fun populateAcademicYear(sBaseURL: String, sToken: String) {
        mastersViewModel.populateManufactureYear(
            sBaseURL + POPULATE_MASTER_LIST, sToken,
            MasterTableNames.MASTERS_ENQUIRY_CALENDAR_YEAR
        )
        mastersViewModel.populateManufactureYearMutableData.observe(viewLifecycleOwner) {
            academicYearList = it
        }
    }

    private fun showAcademicYearDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.academic_year)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SM_AcademicYear(
                academicYearList,
                SM_AcademicYear.OnItemClick {
                    cboAcademicYear!!.text = it.sName
                    iAcademicYearID = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
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
            adapter = SM_AcademicBoard(
                boardItems,
                SM_AcademicBoard.OnItemClick {
                    cboAcademicBoard!!.text = it.sName
                    iAcademicBoard = it.id
                    if (iAcademicBoard > 0) {
                        if (iSettingsID == 1) {
                            populateClass(iAcademicBoard)
                        } else {
                            populateSemesterClass(iAcademicBoard)
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
                adapter = SM_Class(
                    classItems,
                    SM_Class.OnItemClick {
                        cboClass!!.text = it.sClassStandard
                        iClassID = it.iClassStandardID
                        if (iAcademicBoard > 0) {
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
        subjectManagementViewModel.populateSubjectList(
            PopulateSubjectListParams(
                url = sBaseURL,
                sAuthorization = sToken,
                iGroupID = 1,
                iSchoolID = 1,
                iBoardID = iAcademicBoard,
                iClassID = iClassID,
                iYearID = -1,
                iStatusID = -1
            )
        )
        subjectManagementViewModel.populateMutableSubjectList.observe(viewLifecycleOwner) {
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
            adapter = SM_Subject(
                populateSubjectList,
                SM_Subject.OnItemClick {
                    cboSubject!!.text = it.sSubjectName
                    iSubjectID = it.iSubjectID
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
                sErrorSource = SubjectManagementInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

}