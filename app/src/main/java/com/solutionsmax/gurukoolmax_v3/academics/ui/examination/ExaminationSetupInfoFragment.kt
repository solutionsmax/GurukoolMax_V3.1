package com.solutionsmax.gurukoolmax_v3.academics.ui.examination

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
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
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.PopulateExamScheduleListItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_setup.PostExamSetupInfoItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectList
import com.solutionsmax.gurukoolmax_v3.academics.ui.AcademicsViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.SubjectManagementViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.examination.spinner_adapter.exam_setup.*
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_ACADEMICS_ASSIGNMENT_CATEGORIES
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_ENQUIRY_CALENDAR_YEAR
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
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getTime
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentExaminationSetupInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class ExaminationSetupInfoFragment : BaseFragment() {
    private lateinit var binding: FragmentExaminationSetupInfoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var academicsViewModel: AcademicsViewModel
    private lateinit var subjectManagementViewModel: SubjectManagementViewModel
    private lateinit var examScheduleViewModel: ExaminationScheduleViewModel
    private lateinit var examSetupViewModel: ExaminationSetupViewModel


    companion object {
        var cboBoard: TextView? = null
        var iBoardID = -1
        var cboClass: TextView? = null
        var iClassID = -1
        var cboAcademicYear: TextView? = null
        var iAcademicYearID = -1
        var cboExaminationSchedule: TextView? = null
        var iExaminationScheduleID = -1
        var cboSubject: TextView? = null
        var iSubjectID = -1
        var cboFocusAssignment: TextView? = null
        var iFocusAssignmentID = -1
        var dialog: Dialog? = null
        val TIME_DIALOG_ID = 1111
    }


    private lateinit var timePickerDialog: TimePickerDialog

    private lateinit var academicYearList: List<PopulateMasterListItem>
    private lateinit var focusList: List<PopulateMasterListItem>
    private lateinit var boardItems: List<PopulateMasterListItem>
    private lateinit var classItems: List<PopulateClassItems>
    private lateinit var populateSubjectList: List<PopulateSubjectList>
    private lateinit var populateExamScheduleList: List<PopulateExamScheduleListItem>

    private var date: String = ""
    private var cal: Calendar = Calendar.getInstance()
    private var iEditID: Int = -1
    private var sClassMethod = ""
    private var iSettingsID = -1

    val mcurrentTime = Calendar.getInstance()
    val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
    val minutes = mcurrentTime.get(Calendar.MINUTE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentExaminationSetupInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            title = getString(R.string.examination_registration)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            val bundle = bundleOf("menu" to Academics.EXAMINATION_MANAGEMENT)
            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.examinationSetupListFragment, bundle
                )
            }
        }

        iEditID = requireArguments().getInt("id", -1)

        date = SimpleDateFormat("ddMMyyyyhhmmss", Locale.getDefault()).format(Date())

        cboBoard = view.findViewById(R.id.cboBoard)
        cboClass = view.findViewById(R.id.cboClass)
        cboAcademicYear = view.findViewById(R.id.cboAcademicYear)
        cboExaminationSchedule = view.findViewById(R.id.cboExaminationSchedule)
        cboSubject = view.findViewById(R.id.cboSubject)
        cboFocusAssignment = view.findViewById(R.id.cboFocusAssignment)

        val examDateListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                examDate()
            }

        binding.lblExamDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), examDateListener, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.lblStartTime.setOnClickListener {
            timePickerDialog = TimePickerDialog(
                context, { _, hourOfDay, minute ->
                    binding.lblStartTime.text = getTime(hourOfDay,minute)
                }, hour, minutes, true
            )
            timePickerDialog.show()
        }

        binding.lblEndTime.setOnClickListener {

            timePickerDialog = TimePickerDialog(
                context, { _, hourOfDay, minute ->
                    binding.lblEndTime.text = getTime(hourOfDay,minute)
                }, hour, minutes, true
            )
            timePickerDialog.show()
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
        examScheduleViewModel =
            ViewModelProvider(this, viewModelFactory)[ExaminationScheduleViewModel::class.java]
        examSetupViewModel =
            ViewModelProvider(this, viewModelFactory)[ExaminationSetupViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        settingsViewModel.retrieveSettings()
        setUpObservers()

        cboFocusAssignment!!.setOnClickListener {
            showFocusAssignment()
        }

        cboAcademicYear!!.setOnClickListener {
            showAcademicYearDialog()
        }

        cboBoard!!.setOnClickListener {
            showBoardDialog()
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

        cboExaminationSchedule!!.setOnClickListener {
            if (::populateExamScheduleList.isInitialized) {
                shoExamScheduleDialog()
            }
        }

        binding.btnSubmit.setOnClickListener {
            if (iBoardID == -1 || iClassID == -1 || iAcademicYearID == -1 || iExaminationScheduleID == -1 ||
                iSubjectID == -1 || iFocusAssignmentID == -1 || binding.lblExamDate.text == "" ||
                TextUtils.isEmpty(binding.txtMarksAllocated.text) || binding.lblStartTime.text == "" ||
                binding.lblEndTime.text == "" || TextUtils.isEmpty(binding.txtExamDetails.text)
            ) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                examSetupViewModel.checkDuplicateExamSetupInfo(
                    CheckDuplicateExamParams(
                        url = sBaseURL,
                        sAuthorization = sToken,
                        iGroupID = 1,
                        iSchoolID = 1,
                        iBoardID = iBoardID,
                        iClassID = iClassID,
                        iYearID = iAcademicYearID,
                        iScheduledNameID = iExaminationScheduleID,
                        iSubjectID = iSubjectID,
                        tStartTime = binding.lblStartTime.text.toString(),
                        tEndTime = binding.lblEndTime.text.toString(),
                        dExamDate = binding.lblExamDate.text.toString(),
                        iStatusID = 1
                    )
                )
            }
        }
    }

    private fun examDate() {
        val sdf = SimpleDateFormat(DateUtils.DATE_FORMAT, Locale.US)
        binding.lblExamDate.text = sdf.format(cal.time)
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

                populateExamFocusAssignment(sBaseURL, sToken)
                populateExamAcademicYear(sBaseURL, sToken)
                populateAcademicBoard(sBaseURL, sToken)

                if (iEditID > 0) {
                    retrieveDetails(iEditID)
                }
            }
            with(examSetupViewModel) {
                errorLiveData.observe(viewLifecycleOwner) {
                    showError(error = it.peekContent())
                    with(errorLogsViewModel) {
                        postErrors(it)
                        postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                    }
                }
                retrieveExamSetupDetailsMutableData.observe(viewLifecycleOwner) { details ->
                    for (items in details) {
                        cboBoard!!.text = items.sAcademicBoard
                        iBoardID = items.iAcademicBoardID
                        cboClass!!.text = items.sClassStandard
                        iClassID = items.iClassStandardID
                        cboAcademicYear!!.text = items.sCalendarYear
                        iAcademicYearID = items.iCalendarYearID
                        cboExaminationSchedule!!.text = items.sScheduleName
                        iExaminationScheduleID = items.iScheduleNameID
                        cboSubject!!.text = items.sSubjectName
                        iSubjectID = items.iSubjectID
                        binding.lblExamDate.text = items.sDateOfExam
                        cboFocusAssignment!!.text = items.sFocusAssignment
                        iFocusAssignmentID = items.iFocusAssignmentID
                        binding.txtMarksAllocated.setText(items.iMarksAllocated.toString())
                        binding.lblStartTime.text = items.sStartTime
                        binding.lblEndTime.text = items.sEndTime
                        binding.txtExamDetails.setText(items.sAssignmentDetails)
                        binding.txtComments.setText(items.sRemarksComments)
                    }
                }

                checkDuplicateExaminationSetupMutableData.observe(viewLifecycleOwner) { duplicate ->
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

                postExaminationSetupMutableData.observe(viewLifecycleOwner) { post ->
                    if (post > 0) {
                        currentNavController.navigate(R.id.examinationSetupListFragment)
                    } else {
                        showError(
                            getString(R.string.could_not_save_info),
                            getString(R.string.could_not_save_info_desc)
                        )
                    }
                }
                amendExaminationSetupMutableData.observe(viewLifecycleOwner) { amend ->
                    if (amend > 0) {
                        currentNavController.navigate(R.id.examinationSetupListFragment)
                    } else {
                        showError(
                            getString(R.string.could_not_save_info),
                            getString(R.string.could_not_save_info_desc)
                        )
                    }
                }
            }
        }
    }

    private fun postInfo() {
        examSetupViewModel.postExamSetupInfo(
            PostExamSetupParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postExamSetupInfoItem = PostExamSetupInfoItem(
                    iGroupID = 1,
                    iSchoolID = 1,
                    iAcademicBoardID = iBoardID,
                    iClassStandardID = iClassID,
                    iCalendarYearID = iAcademicYearID,
                    iScheduleNameID = iExaminationScheduleID,
                    iSubjectID = iSubjectID,
                    iFocusAssignmentID = iFocusAssignmentID,
                    sStartTime = binding.lblStartTime.text.toString(),
                    sEndTime = binding.lblEndTime.text.toString(),
                    iMarksAllocated = Integer.parseInt(binding.txtMarksAllocated.text.toString()),
                    sAssignmentDetails = binding.txtExamDetails.text.toString(),
                    sRemarksComments = binding.txtComments.text.toString(),
                    sDateOfExam = binding.lblExamDate.text.toString(),
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    iWorkflowStatusID = 1,
                    iApprovedByID = 1,
                    sApprovedDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    private fun amendInfo() {
        examSetupViewModel.amendExamSetupInfo(
            PostExamSetupParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postExamSetupInfoItem = PostExamSetupInfoItem(
                    id = iEditID,
                    iGroupID = 1,
                    iSchoolID = 1,
                    iAcademicBoardID = iBoardID,
                    iClassStandardID = iClassID,
                    iCalendarYearID = iAcademicYearID,
                    iScheduleNameID = iExaminationScheduleID,
                    iSubjectID = iSubjectID,
                    iFocusAssignmentID = iFocusAssignmentID,
                    sStartTime = binding.lblStartTime.text.toString(),
                    sEndTime = binding.lblEndTime.text.toString(),
                    iMarksAllocated = Integer.parseInt(binding.txtMarksAllocated.text.toString()),
                    sAssignmentDetails = binding.txtExamDetails.text.toString(),
                    sRemarksComments = binding.txtComments.text.toString(),
                    sDateOfExam = binding.lblExamDate.text.toString(),
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    iWorkflowStatusID = 1,
                    iApprovedByID = 1,
                    sApprovedDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    private fun retrieveDetails(iEditID: Int) {
        examSetupViewModel.retrieveExamSetupDetails(
            RetrieveExamSetupDetailsParams(
                url = sBaseURL,
                sAuthorization = sToken,
                id = iEditID
            )
        )
    }

    /**
     * Populate Focus Assignment
     */
    private fun populateExamFocusAssignment(sBaseURL: String, sToken: String) {
        mastersViewModel.populateFocusAssignment(
            sBaseURL + MethodConstants.POPULATE_MASTER_LIST,
            sToken,
            MASTERS_ACADEMICS_ASSIGNMENT_CATEGORIES
        )
        mastersViewModel.populateFocusAssignmentMutableData.observe(viewLifecycleOwner) {
            focusList = it
        }
    }

    private fun showFocusAssignment() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.academic_year)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ExamSetupFocusAssignment(
                focusList,
                ExamSetupFocusAssignment.OnItemClick {
                    cboFocusAssignment!!.text = it.sName
                    iFocusAssignmentID = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    /**
     * Populate Academic year
     */
    private fun populateExamAcademicYear(sBaseURL: String, sToken: String) {
        mastersViewModel.populateAcademicYear(
            sBaseURL + MethodConstants.POPULATE_MASTER_LIST, sToken, MASTERS_ENQUIRY_CALENDAR_YEAR
        )
        mastersViewModel.populateAcademicYearMutableData.observe(viewLifecycleOwner) {
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
            adapter = ExamSetupAcademicYear(
                academicYearList,
                ExamSetupAcademicYear.OnItemClick {
                    cboAcademicYear!!.text = it.sName
                    iAcademicYearID = it.id
                    if (iBoardID > 0 && iAcademicYearID > 0) {
                        populateExamSchedule(iBoardID, iAcademicYearID)
                    }
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
            adapter = ExamSetupBoard(
                boardItems,
                ExamSetupBoard.OnItemClick {
                    cboBoard!!.text = it.sName
                    iBoardID = it.id
                    if (iBoardID > 0) {
                        if (iSettingsID == 1) {
                            populateClass(iBoardID)
                        } else {
                            populateSemesterClass(iBoardID)
                        }
                    }
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    /**
     * Populate Examination Schedule
     */
    private fun populateExamSchedule(iBoardID: Int, iAcademicYearID: Int) {
        examScheduleViewModel.populateExamSchedule(
            PopulateExamScheduleParams(
                url = sBaseURL,
                sAuthorization = sToken,
                iGroupID = 1,
                iSchoolID = 1,
                iBoardID = iBoardID,
                iYearID = iAcademicYearID,
                iStatusID = -1
            )
        )
        examScheduleViewModel.populateMutableExamSchedule.observe(viewLifecycleOwner) {
            populateExamScheduleList = it
        }
    }

    private fun shoExamScheduleDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.examination_schedule)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ExamSetupExamSchedule(
                populateExamScheduleList,
                ExamSetupExamSchedule.OnItemClick {
                    cboBoard!!.text = it.sScheduleName
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
                adapter = ExamSetupClass(
                    classItems,
                    ExamSetupClass.OnItemClick {
                        cboClass!!.text = it.sClassStandard
                        iClassID = it.iClassStandardID
                        if (iBoardID > 0) {
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
                iBoardID = iBoardID,
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
            adapter = ExamSetupSubject(
                populateSubjectList,
                ExamSetupSubject.OnItemClick {
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
                sErrorSource = ExaminationSetupInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }
}