package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions

import android.app.DatePickerDialog
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
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.PopulateCurriculumSessionTopicList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.learning_session.PostLearningSessionItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.map_subject_to_faculty.PopulateMapSubjectToFacultyItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectProgramList
import com.solutionsmax.gurukoolmax_v3.academics.ui.AcademicsViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.SubjectManagementViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus.CurriculumInfoViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.spinner_adapter.*
import com.solutionsmax.gurukoolmax_v3.academics.ui.map_subject_to_faculty.MapSubjectToFacultyViewModel
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
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentSetupLearningSessionInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class SetupLearningSessionInfoFragment : BaseFragment() {
    private lateinit var binding: FragmentSetupLearningSessionInfoBinding

    companion object {
        var cboAcademicYear: TextView? = null
        var iAcademicYearID = -1
        var cboAcademicBoard: TextView? = null
        var iAcademicBoard = -1
        var cboClass: TextView? = null
        var iClassId = -1
        var cboSubject: TextView? = null
        var iSubjectID = -1
        var cboFaculty: TextView? = null
        var iFacultyID = -1
        var cboCurriculumSection: TextView? = null
        var iCurriculumSection = -1
        var cboTopic: TextView? = null
        var iTopicID = -1
        var dialog: Dialog? = null
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var academicsViewModel: AcademicsViewModel
    private lateinit var subjectManagementViewModel: SubjectManagementViewModel
    private lateinit var curriculumInfoViewModel: CurriculumInfoViewModel
    private lateinit var setupLearningSessionViewModel: SetupLearningSessionViewModel
    private lateinit var mapSubjectToFacultyViewModel: MapSubjectToFacultyViewModel

    private var academicYearList: List<PopulateMasterListItem> = emptyList()
    private var boardItems: List<PopulateMasterListItem> = emptyList()
    private var classItems: List<PopulateClassItems> = emptyList()
    private var populateSubjectList: List<PopulateSubjectProgramList> = emptyList()
    private var populateCurriculumList: List<PopulateCurriculumSessionTopicList> = emptyList()
    private var populateFaculty: List<PopulateMapSubjectToFacultyItem> = emptyList()
    private var populateTopicList: List<PopulateCurriculumSessionTopicList> = emptyList()

    private var date: String = ""
    private var cal: Calendar = Calendar.getInstance()

    private var iEditID = -1
    private var sClassMethod = ""
    private var iSettingsID = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSetupLearningSessionInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            title = getString(R.string.link_class_sections_with_subject)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.setupLearningSessionListFragment
                )
            }
        }

        date = SimpleDateFormat("ddMMyyyyhhmmss", Locale.getDefault()).format(Date())

        iEditID = requireArguments().getInt("id", -1)

        cboAcademicYear = view.findViewById(R.id.cboAcademicYear)
        cboAcademicBoard = view.findViewById(R.id.cboAcademicBoard)
        cboClass = view.findViewById(R.id.cboClass)
        cboSubject = view.findViewById(R.id.cboSubject)
        cboFaculty = view.findViewById(R.id.cboFaculty)
        cboCurriculumSection = view.findViewById(R.id.cboCurriculumSection)
        cboTopic = view.findViewById(R.id.cboTopic)

        val examDateListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                startDate()
            }

        binding.lblStartDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), examDateListener, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
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
        curriculumInfoViewModel =
            ViewModelProvider(this, viewModelFactory)[CurriculumInfoViewModel::class.java]
        setupLearningSessionViewModel =
            ViewModelProvider(this, viewModelFactory)[SetupLearningSessionViewModel::class.java]
        mapSubjectToFacultyViewModel =
            ViewModelProvider(this, viewModelFactory)[MapSubjectToFacultyViewModel::class.java]

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

        cboFaculty!!.setOnClickListener {
            showFacultyDialog()
        }

        cboCurriculumSection!!.setOnClickListener {
            showCurriculumDialog()
        }

        cboTopic!!.setOnClickListener {
            showTopicDialog()
        }

        binding.btnSubmit.setOnClickListener {
            if (iAcademicYearID == -1 || iAcademicBoard == -1 || iClassId == -1 || iSubjectID == -1 ||
                iFacultyID == -1 || iCurriculumSection == -1 || iTopicID == -1 ||
                TextUtils.isEmpty(binding.txtSessionTopicCovered.text)
            ) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                binding.progressBar.visibility = View.VISIBLE
                setupLearningSessionViewModel.checkDuplicateLearningSession(
                    CheckDuplicateLearningSessionProgressParams(
                        url = sBaseURL,
                        sToken = sToken,
                        iGroupID = 1,
                        iSchoolID = 1,
                        iBoardID = iAcademicBoard,
                        iClassStandardID = iClassId,
                        iYearID = iAcademicYearID,
                        iSubjectID = iSubjectID,
                        iSectionID = iCurriculumSection,
                        iSessionTopicID = iTopicID,
                        sTopicDetails = binding.txtSessionTopicCovered.text.toString()
                    )
                )
            }
        }
    }

    private fun startDate() {
        val sdf = SimpleDateFormat(DateUtils.DATE_FORMAT, Locale.US)
        binding.lblStartDate.text = sdf.format(cal.time)
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
                populateAcademicYearList(sBaseURL, sToken)

                if (iEditID > 0) {
                    retrieveDetails(iEditID)
                }
            }
            with(setupLearningSessionViewModel) {
                retrieveLearningSessionDetailsMutableData.observe(viewLifecycleOwner) { details ->
                    for (items in details) {
                        cboAcademicYear!!.text = items.sAcademicYear
                        iAcademicYearID = items.iAcademicYearID
                        cboAcademicBoard!!.text = items.sAcademicBoard
                        iAcademicBoard = items.iAcademicBoardID
                        cboClass!!.text = items.sClassStandard
                        iClassId = items.iClassStandardID
                        cboSubject!!.text = items.sSubjectName
                        iSubjectID = items.iSubjectID
                        cboFaculty!!.text = items.sFacultyName
                        iFacultyID = items.iFacultyID
                        cboCurriculumSection!!.text = items.sCurriculumSection
                        iCurriculumSection = items.iCurriculumSectionID
                        binding.lblStartDate.text = items.sStartDateFormat
                        cboTopic!!.text = items.sCurriculumSectionTopic
                        iTopicID = items.iSessionTopicID
                        binding.txtSessionTopicCovered.setText(items.sSessionTopicDetails)
                        populateAcademicBoard(sBaseURL, sToken)
                        populateAcademicYearList(sBaseURL, sToken)
                    }
                }

                checkDuplicateLearningSessionProgressMutableData.observe(viewLifecycleOwner) { duplicate ->
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

                postLearningSessionMutableData.observe(viewLifecycleOwner) { post ->
                    if (post > 0) {
                        currentNavController.navigate(R.id.setupLearningSessionListFragment)
                    } else {
                        binding.progressBar.visibility = View.INVISIBLE
                        showError(
                            getString(R.string.could_not_save_info),
                            getString(R.string.could_not_save_info_desc)
                        )
                    }
                }
                amendLearningSessionMutableData.observe(viewLifecycleOwner) { amend ->
                    if (amend > 0) {
                        currentNavController.navigate(R.id.setupLearningSessionListFragment)
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
    }

    private fun postInfo() {
        setupLearningSessionViewModel.postLearningSessionInfo(
            PostLearningSessionParams(
                url = sBaseURL,
                sToken = sToken,
                postLearningSessionItem = PostLearningSessionItem(
                    iGroupID = 1,
                    iSchoolID = 1,
                    iAcademicBoardID = iAcademicBoard,
                    iClassStandardID = iClassId,
                    iSubjectID = iSubjectID,
                    iCurriculumSectionID = iCurriculumSection,
                    iSessionTopicID = iTopicID,
                    sSessionTopicDetails = binding.txtSessionTopicCovered.text.toString(),
                    iFacultyID = iFacultyID,
                    sStartDate = binding.lblStartDate.text.toString(),
                    sCompletionDate = "",
                    sFacultyRemarks = "",
                    iProgressStatus = 0,
                    iAcademicYearID = iAcademicYearID,
                    iWorkflowStatusID = 1,
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    private fun amendInfo() {
        setupLearningSessionViewModel.amendLearningSessionInfo(
            PostLearningSessionParams(
                url = sBaseURL,
                sToken = sToken,
                postLearningSessionItem = PostLearningSessionItem(
                    id = iEditID,
                    iGroupID = 1,
                    iSchoolID = 1,
                    iAcademicBoardID = iAcademicBoard,
                    iClassStandardID = iClassId,
                    iSubjectID = iSubjectID,
                    iCurriculumSectionID = iCurriculumSection,
                    iSessionTopicID = iTopicID,
                    sSessionTopicDetails = binding.txtSessionTopicCovered.text.toString(),
                    iFacultyID = iFacultyID,
                    sStartDate = binding.lblStartDate.text.toString(),
                    sCompletionDate = "",
                    sFacultyRemarks = "",
                    iProgressStatus = 0,
                    iAcademicYearID = iAcademicYearID,
                    iWorkflowStatusID = 1,
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    /**
     * Retrieve Details
     */
    private fun retrieveDetails(iEditID: Int) {
        setupLearningSessionViewModel.retrieveLearningSessionDetailsInfo(
            RetrieveLearningSessionDetailsParams(
                url = sBaseURL,
                sToken = sToken,
                id = iEditID
            )
        )
    }

    /**
     * Populate Academic year
     */
    private fun populateAcademicYearList(sBaseURL: String, sToken: String) {
        mastersViewModel.populateAcademicYear(
            sBaseURL + MethodConstants.POPULATE_MASTER_LIST, sToken,
            MasterTableNames.MASTERS_ENQUIRY_CALENDAR_YEAR
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
            adapter = SLSAcademicYear(
                academicYearList,
                SLSAcademicYear.OnItemClick {
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
            adapter = SLSBoard(
                boardItems,
                SLSBoard.OnItemClick {
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
                adapter = SLSClass(
                    classItems,
                    SLSClass.OnItemClick {
                        cboClass!!.text = it.sClassStandard
                        iClassId = it.iClassStandardID
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
        subjectManagementViewModel.populateSubjectProgramList(
            PopulateSubjectProgramsListParams(
                url = sBaseURL,
                sAuthorization = sToken,
                iGroupID = 1,
                iSchoolID = 1,
                iBoardID = iAcademicBoard,
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
            adapter = SLSSubject(
                populateSubjectList,
                SLSSubject.OnItemClick {
                    cboSubject!!.text = it.sSubjectName
                    iSubjectID = it.id
                    if (iAcademicBoard > 0 && iClassId > 0 && iSubjectID > 0) {
                        populateCurriculum()
                        populateFaculty()
                        populateTopic()
                    }
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    /**
     * Populate Faculty
     */
    private fun populateFaculty() {
        mapSubjectToFacultyViewModel.populateMapSubjectToFaculty(
            PopulateMapSubjectToFacultyParams(
                url = sBaseURL,
                sAuthorization = sToken,
                iGroupID = 1,
                iSchoolID = 1,
                iBoardID = iAcademicBoard,
                iClassID = iClassId,
                iFacultyID = -1,
                iSubjectID = iSubjectID,
                iStatusID = -1
            )
        )
        mapSubjectToFacultyViewModel.populateMapSubjectToFacultyMutableData.observe(
            viewLifecycleOwner
        ) {
            populateFaculty = it
        }
    }

    private fun showFacultyDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.faculty)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SLSFaculty(
                populateFaculty,
                SLSFaculty.OnItemClick {
                    cboSubject!!.text = it.sFacultyName
                    iSubjectID = it.iFacultyID
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    /**
     * Populate Topic
     */
    private fun populateTopic() {
        curriculumInfoViewModel.populateSessionTopicList(
            PopulateCurriculumSessionTopicListParams(
                url = sBaseURL,
                sAuthorization = sToken,
                iGroupID = 1,
                iSchoolID = 1,
                iBoardID = iAcademicBoard,
                iGradeID = iClassId,
                iSubjectID = iSubjectID,
                iStatusID = -1
            )
        )
        curriculumInfoViewModel.populateMutableSessionTopicList.observe(viewLifecycleOwner) {
            populateTopicList = it
        }
    }

    private fun showTopicDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.faculty)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SLSTopicList(
                populateTopicList,
                SLSTopicList.OnItemClick {
                    cboTopic!!.text = it.sSectionTitle
                    iTopicID = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    /**
     * Populate Curriculum
     */
    private fun populateCurriculum() {
        curriculumInfoViewModel.populateCurriculumListByClassReference(
            PopulateCurriculumSessionTopicListParams(
                url = sBaseURL,
                sAuthorization = sToken,
                iGroupID = 1,
                iSchoolID = 1,
                iBoardID = iAcademicBoard,
                iGradeID = iClassId,
                iSubjectID = iSubjectID,
                iStatusID = -1
            )
        )
        curriculumInfoViewModel.populateMutableCurriculumListByClassReference.observe(
            viewLifecycleOwner
        ) {
            populateCurriculumList = it
        }
    }

    private fun showCurriculumDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.curriculum_section)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SLSCurriculum(
                populateCurriculumList,
                SLSCurriculum.OnItemClick {
                    cboCurriculumSection!!.text = it.sSectionTitle
                    iCurriculumSection = it.id
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
                sErrorSource = SetupLearningSessionInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

}