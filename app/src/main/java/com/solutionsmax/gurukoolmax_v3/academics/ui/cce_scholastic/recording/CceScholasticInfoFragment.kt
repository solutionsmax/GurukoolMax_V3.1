package com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.recording

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
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common.PopulateClassItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.PopulateExamScheduleListItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.PopulateStudentScholasticGradingItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.PostScholasticRecordingInfoItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectProgramList
import com.solutionsmax.gurukoolmax_v3.academics.ui.AcademicsViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.recording.spinner_adapter.*
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.SubjectManagementViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.examination.ExaminationScheduleViewModel
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames
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
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentCceScholasticInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject

class CceScholasticInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentCceScholasticInfoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var academicsViewModel: AcademicsViewModel
    private lateinit var subjectManagementViewModel: SubjectManagementViewModel
    private lateinit var cceRecordingViewModel: CceRecordingViewModel
    private lateinit var examinationSetupViewModel: ExaminationScheduleViewModel

    companion object {
        var cboAcademicBoard: TextView? = null
        var iBoardID = -1
        var cboClass: TextView? = null
        var iClassID = -1
        var cboStudent: TextView? = null
        var iStudentID = -1
        var cboSubject: TextView? = null
        var iSubjectID = -1
        var cboScholasticCategory: TextView? = null
        var iScholasticCategoryID = -1
        var dialog: Dialog? = null
    }

    private lateinit var boardItems: List<PopulateMasterListItem>
    private lateinit var classItems: List<PopulateClassItems>
    private lateinit var populateSubjectList: List<PopulateSubjectProgramList>
    private lateinit var populateStudentList: List<PopulateStudentScholasticGradingItem>
    private lateinit var populateExamSchedule: List<PopulateExamScheduleListItem>

    private var iEditID = -1
    private var sClassMethod = ""
    private var iSettingsID = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCceScholasticInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            title = getString(R.string.scholastic_management)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.cceScholasticListFragment
                )
            }
        }

        iEditID = requireArguments().getInt("id", -1)

        cboAcademicBoard = view.findViewById(R.id.cboAcademicBoard)
        cboClass = view.findViewById(R.id.cboClass)
        cboStudent = view.findViewById(R.id.cboStudent)
        cboSubject = view.findViewById(R.id.cboSubject)
        cboScholasticCategory = view.findViewById(R.id.cboScholasticCategory)

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
        cceRecordingViewModel =
            ViewModelProvider(this, viewModelFactory)[CceRecordingViewModel::class.java]
        examinationSetupViewModel =
            ViewModelProvider(this, viewModelFactory)[ExaminationScheduleViewModel::class.java]

        binding.btnSubmit.setOnClickListener {
            currentNavController.navigate(
                R.id.cceScholasticListFragment
            )
        }

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        settingsViewModel.retrieveSettings()
        setUpObservers()

        cboAcademicBoard!!.setOnClickListener {
            showBoardDialog()
        }

        cboScholasticCategory!!.setOnClickListener {
            if (iBoardID > 0) {
                populateScholasticCategory(iBoardID)
            }
            showScholasticCategory()
        }

        cboClass!!.setOnClickListener {
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

        cboStudent!!.setOnClickListener {
            if (iBoardID > 0 && iClassID > 0) {
                populateStudent(iBoardID, iClassID)
            }
            if (::populateStudentList.isInitialized) {
                showStudentDialog()
            }
        }

        cboSubject!!.setOnClickListener {
            if (iBoardID > 0) {
                populateSubject()
            }
            if (::populateSubjectList.isInitialized) {
                showSubjectDialog()
            }
        }

        binding.btnSubmit.setOnClickListener {
            if (iBoardID == -1 || iClassID == -1 || iStudentID == -1 || iSubjectID == -1 || iScholasticCategoryID == -1 ||
                TextUtils.isEmpty(binding.txtMarksSecured.text)
            ) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                cceRecordingViewModel.checkDuplicateScholasticRecording(
                    CheckDuplicateScholasticRecordingParams(
                        url = sBaseURL,
                        sAuthorization = sToken,
                        iGroupID = 1,
                        iSchoolID = 1,
                        iBoardID = iBoardID,
                        iClassID = iClassID,
                        iSubjectID = iSubjectID,
                        iStudentID = iStudentID,
                        iScholarCategoryID = iScholasticCategoryID
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

                populateAcademicBoard(sBaseURL, sToken)

                if (iEditID > 0) {
                    retrieveDetails(iEditID)
                }
            }
            with(cceRecordingViewModel) {
                errorLiveData.observe(viewLifecycleOwner) {
                    showError(error = it.peekContent())
                    with(errorLogsViewModel) {
                        postErrors(it)
                        postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                    }
                }
                checkDuplicateMutableDataScholasticRecording.observe(viewLifecycleOwner) { duplicate ->
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

                postMutableDataScholasticRecording.observe(viewLifecycleOwner) { post ->
                    if (post > 0) {
                        currentNavController.navigate(R.id.cceScholasticListFragment)
                    } else {
                        showError(
                            getString(R.string.could_not_save_info),
                            getString(R.string.could_not_save_info_desc)
                        )
                    }
                }

                amendMutableDataScholasticRecording.observe(viewLifecycleOwner) { amend ->
                    if (amend > 0) {
                        currentNavController.navigate(R.id.cceScholasticListFragment)
                    } else {
                        showError(
                            getString(R.string.could_not_save_info),
                            getString(R.string.could_not_save_info_desc)
                        )
                    }
                }

                retrieveMutableDataScholasticRecordingDetails.observe(viewLifecycleOwner) { details ->
                    for (items in details) {
                        cboAcademicBoard!!.text = items.sAcademicBoard
                        iBoardID = items.iAcademicBoardID
                        cboClass!!.text = items.sClassStandard
                        iClassID = items.iClassStandardID
                        cboSubject!!.text = items.sSubjectName
                        iSubjectID = items.iSubjectID
                        cboStudent!!.text = items.sStudentName
                        iStudentID = items.iStudentID
                        cboScholasticCategory!!.text = items.sScholasticCategory
                        iScholasticCategoryID = items.iScholasticCategoryID
                        binding.txtMarksSecured.setText(items.iMarksSecured.toString())
                    }
                }
            }
        }
    }

    private fun postInfo() {
        cceRecordingViewModel.postScholasticRecording(
            PostScholasticRecordingParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postScholasticRecordingInfoItem = PostScholasticRecordingInfoItem(
                    iGroupID = 1,
                    iSchoolID = 1,
                    iStudentID = iStudentID,
                    iAcademicBoardID = iBoardID,
                    iClassStandardID = iClassID,
                    iSubjectID = iSubjectID,
                    iScholasticCategoryID = iScholasticCategoryID,
                    iMarksSecured = Integer.parseInt(binding.txtMarksSecured.text.toString()),
                    iUserID = 1,
                    iWorkflowStatusID = 1,
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    private fun amendInfo() {
        cceRecordingViewModel.amendScholasticRecording(
            PostScholasticRecordingParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postScholasticRecordingInfoItem = PostScholasticRecordingInfoItem(
                    id = iEditID,
                    iGroupID = 1,
                    iSchoolID = 1,
                    iStudentID = iStudentID,
                    iAcademicBoardID = iBoardID,
                    iClassStandardID = iClassID,
                    iSubjectID = iSubjectID,
                    iScholasticCategoryID = iScholasticCategoryID,
                    iMarksSecured = Integer.parseInt(binding.txtMarksSecured.text.toString()),
                    iUserID = 1,
                    iWorkflowStatusID = 1,
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    private fun retrieveDetails(iEditID: Int) {
        cceRecordingViewModel.retrieveScholasticRecordingDetails(
            RetrieveScholasticRecordingDetailsParams(
                url = sBaseURL,
                sAuthorization = sToken,
                id = iEditID
            )
        )
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
            adapter = CCEBoard(
                boardItems,
                CCEBoard.OnItemClick {
                    cboAcademicBoard!!.text = it.sName
                    iBoardID = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    /**
     * Populate Scholastic Category
     */
    private fun populateScholasticCategory(iBoardID: Int) {
        examinationSetupViewModel.populateExamSchedule(
            PopulateExamScheduleParams(
                url = sBaseURL,
                sAuthorization = sToken,
                iGroupID = 1,
                iSchoolID = 1,
                iBoardID = iBoardID,
                iYearID = -1,
                iStatusID = -1
            )
        )
        examinationSetupViewModel.populateMutableExamSchedule.observe(viewLifecycleOwner) {
            populateExamSchedule = it
        }
    }

    private fun showScholasticCategory() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.academic_board)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CCEScholasticCategory(
                populateExamSchedule,
                CCEScholasticCategory.OnItemClick {
                    cboAcademicBoard!!.text = it.sScheduleName
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

            val dialogHeader: TextView =
                dialog!!.findViewById(R.id.lblDialogHeading)
            dialogHeader.text = getString(R.string.class_standard)

            val dialogRecyclerView: RecyclerView =
                dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
            dialogRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = CCEClass(
                    classItems,
                    CCEClass.OnItemClick {
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

    /**
     * Populate Student
     */
    private fun populateStudent(iBoardID: Int, iClassID: Int) {
        cceRecordingViewModel.populateStudentScholasticList(
            PopulateStudentScholasticListParams(
                url = sBaseURL,
                sAuthorization = sToken,
                iGroupID = 1,
                iSchoolID = 1,
                iBoardID = iBoardID,
                iClassID = iClassID,
                iCategoryID = -1,
                iStatusID = -1
            )
        )
        cceRecordingViewModel.populateMutableDataStudentScholasticList.observe(viewLifecycleOwner) {
            populateStudentList = it
        }
    }

    private fun showStudentDialog() {
        if (populateStudentList.isNotEmpty()) {
            dialog = Dialog(requireContext())
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setCancelable(true)
            dialog!!.setContentView(R.layout.spinner_dialog)

            val dialogHeader: TextView =
                dialog!!.findViewById(R.id.lblDialogHeading)
            dialogHeader.text = getString(R.string.student)

            val dialogRecyclerView: RecyclerView =
                dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
            dialogRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = CCEStudent(
                    populateStudentList,
                    CCEStudent.OnItemClick {
                        cboStudent!!.text = it.sStudentName
                        iStudentID = it.iStudentID
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
                iBoardID = iBoardID,
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
            adapter = CCESubject(
                populateSubjectList,
                CCESubject.OnItemClick {
                    cboSubject!!.text = it.sSubjectName
                    iSubjectID = it.id
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
                sErrorSource = CceScholasticInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

}