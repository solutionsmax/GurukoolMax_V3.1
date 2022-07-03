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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common.PopulateClassItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.km.PostKMInfo
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectList
import com.solutionsmax.gurukoolmax_v3.academics.ui.AcademicsViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.SubjectManagementViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.spinner_adapter.*
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_KNOWLEDGE_MANAGEMENT_CONTENT_TYPES
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_KNOWLEDGE_MANAGEMENT_FORMAT_TYPES
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_KNOWLEDGE_MANAGEMENT_SOURCES
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
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentKMPostRepositoryInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class KMPostRepositoryInfoFragment : BaseFragment() {
    private lateinit var binding: FragmentKMPostRepositoryInfoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var academicsViewModel: AcademicsViewModel
    private lateinit var subjectManagementViewModel: SubjectManagementViewModel
    private lateinit var kmViewModel: KmViewModel

    companion object {
        var cboBoard: TextView? = null
        var iAcademicBoardID = -1
        var cboClass: TextView? = null
        var iClassID = -1
        var cboSubject: TextView? = null
        var iSubjectID = -1
        var cboContentType: TextView? = null
        var iContentTypeID = -1
        var cboSubmissionCategory: TextView? = null
        var iSubmissionCategoryID = -1
        var cboSubmissionFormat: TextView? = null
        var iSubmissionFormatID = -1
        var dialog: Dialog? = null
    }

    private var boardItems: List<PopulateMasterListItem> = emptyList()
    private var classItems: List<PopulateClassItems> = emptyList()
    private var populateSubjectList: List<PopulateSubjectList> = emptyList()
    private var contentTypesItems: List<PopulateMasterListItem> = emptyList()
    private var submissionCategoryItems: List<PopulateMasterListItem> = emptyList()
    private var formatTypeItems: List<PopulateMasterListItem> = emptyList()

    private var iEditID = -1
    private var sClassMethod = ""
    private var iSettingsID = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentKMPostRepositoryInfoBinding.inflate(inflater)
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
                currentNavController.navigate(
                    R.id.KMRepositoryListFragment
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
        kmViewModel = ViewModelProvider(this, viewModelFactory)[KmViewModel::class.java]

        cboBoard = view.findViewById(R.id.cboBoard)
        cboClass = view.findViewById(R.id.cboClass)
        cboSubject = view.findViewById(R.id.cboSubject)
        cboContentType = view.findViewById(R.id.cboContentType)
        cboSubmissionCategory = view.findViewById(R.id.cboSubmissionCategory)
        cboSubmissionFormat = view.findViewById(R.id.cboSubmissionFormat)

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        settingsViewModel.retrieveSettings()
        setUpObservers()

        cboBoard!!.setOnClickListener {
            showBoardDialog()
        }

        cboContentType!!.setOnClickListener {
            showContentTypeDialog()
        }

        cboSubmissionCategory!!.setOnClickListener {
            showSubmissionCategoryDialog()
        }

        cboSubmissionFormat!!.setOnClickListener {
            showFormatTypesDialog()
        }

        cboClass!!.setOnClickListener {
            showClassDialog()
        }

        cboSubject!!.setOnClickListener {
            showSubjectDialog()
        }

        binding.btnSubmit.setOnClickListener {
            if (iAcademicBoardID == -1 || iClassID == -1 || iSubjectID == -1 || iContentTypeID == -1 || iSubmissionCategoryID == -1 || iSubmissionFormatID == -1
                || TextUtils.isEmpty(binding.txtComments.text) ||
                TextUtils.isEmpty(binding.txtAbstractOfSubmission.text)
            ) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                if (iEditID > 0) {
                    amendInfo()
                } else {
                    postInfo()
                }
            }
        }
    }

    private fun postInfo() {
        kmViewModel.postKmInfo(
            KmPostParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postKMInfo = PostKMInfo(
                    iGroupID = 1,
                    iSchoolID = 1,
                    iAcademicBoardID = iAcademicBoardID,
                    iClassStandardID = iClassID,
                    iSubjectID = iSubjectID,
                    iSubmitterID = 1,
                    sTitle = binding.txtRecommendedTitle.text.toString(),
                    sAbstract = binding.txtAbstractOfSubmission.text.toString(),
                    sContentDescription = binding.txtComments.text.toString(),
                    iFormatTypeID = iSubmissionFormatID,
                    iContentTypeID = iContentTypeID,
                    iSourceID = iSubmissionCategoryID,
                    sContentLink = "",
                    sFileName = "",
                    sTagKeyWords = binding.txtTaggedKeywords.text.toString(),
                    iWorkflowStatusID = 1,
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    private fun amendInfo() {
        kmViewModel.amendKmInfo(
            KmPostParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postKMInfo = PostKMInfo(
                    id = iEditID,
                    iGroupID = 1,
                    iSchoolID = 1,
                    iAcademicBoardID = iAcademicBoardID,
                    iClassStandardID = iClassID,
                    iSubjectID = iSubjectID,
                    iSubmitterID = 1,
                    sTitle = binding.txtRecommendedTitle.text.toString(),
                    sAbstract = binding.txtAbstractOfSubmission.text.toString(),
                    sContentDescription = binding.txtComments.text.toString(),
                    iFormatTypeID = iSubmissionFormatID,
                    iContentTypeID = iContentTypeID,
                    iSourceID = iSubmissionCategoryID,
                    sContentLink = "",
                    sFileName = "",
                    sTagKeyWords = binding.txtTaggedKeywords.text.toString(),
                    iWorkflowStatusID = 1,
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )

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
                populateSubmissionCategory(sBaseURL, sToken)
                populateSubmissionFormat(sBaseURL, sToken)

                if (iEditID > 0) {
                    retrieveDetails(iEditID)
                }
            }

            with(kmViewModel) {
                mutableKmDetails.observe(viewLifecycleOwner) { details ->
                    for (items in details) {
                        populateAcademicBoard(sBaseURL, sToken)
                        populateContentTypes(sBaseURL, sToken)
                        populateSubmissionCategory(sBaseURL, sToken)
                        populateSubmissionFormat(sBaseURL, sToken)
                        cboBoard!!.text = items.sAcademicBoard
                        iAcademicBoardID = items.iAcademicBoardID
                        cboClass!!.text = items.sClassStandard
                        iClassID = items.iClassStandardID
                        cboSubject!!.text = items.sSubjectName
                        iSubjectID = items.iSubjectID
                        binding.txtRecommendedTitle.setText(items.sTitle)
                        cboContentType!!.text = items.sContentType
                        iContentTypeID = items.iContentTypeID
                        binding.txtAbstractOfSubmission.setText(items.sAbstract)
                        cboSubmissionCategory!!.text = items.sSourceCategory
                        iSubmissionCategoryID = items.iSourceID
                        cboSubmissionFormat!!.text = items.sFormatType
                        iSubmissionFormatID = items.iFormatTypeID
                        binding.txtTaggedKeywords.setText(items.sTagKeyWords)
                        binding.txtComments.setText(items.sContentDescription)
                    }
                }

                mutablePostKmInfo.observe(viewLifecycleOwner) { post ->
                    if (post > 0) {
                        currentNavController.navigate(R.id.KMRepositoryListFragment)
                    } else {
                        showError(
                            getString(R.string.could_not_save_info),
                            getString(R.string.could_not_save_info_desc)
                        )
                    }
                }

                mutableAmendKmInfo.observe(viewLifecycleOwner) { amend ->
                    if (amend > 0) {
                        currentNavController.navigate(R.id.KMRepositoryListFragment)
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

    private fun retrieveDetails(iEditID: Int) {
        kmViewModel.retrieveKmDetails(
            KmRetrieveDetailsParams(
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
            adapter = KM_Board(
                boardItems,
                KM_Board.OnItemClick {
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
                adapter = KM_Class(
                    classItems,
                    KM_Class.OnItemClick {
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
        subjectManagementViewModel.populateSubjectList(
            PopulateSubjectListParams(
                url = sBaseURL,
                sAuthorization = sToken,
                iGroupID = 1,
                iSchoolID = 1,
                iBoardID = iAcademicBoardID,
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
            adapter = KM_Subject(
                populateSubjectList,
                KM_Subject.OnItemClick {
                    cboSubject!!.text = it.sSubjectName
                    iSubjectID = it.iSubjectID
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
            url = sBaseURL + MethodConstants.POPULATE_MASTER_LIST,
            sAuthorization = sToken,
            sTableName = MASTERS_KNOWLEDGE_MANAGEMENT_CONTENT_TYPES
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
            adapter = KM_ContentTypes(
                contentTypesItems,
                KM_ContentTypes.OnItemClick {
                    cboBoard!!.text = it.sName
                    iAcademicBoardID = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    /**
     * Populate SubmissionCategory
     */
    private fun populateSubmissionCategory(sBaseURL: String, sToken: String) {
        mastersViewModel.populateKMSubmissionCategory(
            url = sBaseURL + MethodConstants.POPULATE_MASTER_LIST,
            sAuthorization = sToken,
            sTableName = MASTERS_KNOWLEDGE_MANAGEMENT_SOURCES
        )
        mastersViewModel.populateKmSubmissionCategoryMutableData.observe(viewLifecycleOwner) {
            submissionCategoryItems = it
        }
    }

    private fun showSubmissionCategoryDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.submission_category)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = KM_SubmissionCategory(
                submissionCategoryItems,
                KM_SubmissionCategory.OnItemClick {
                    cboBoard!!.text = it.sName
                    iAcademicBoardID = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    /**
     * Populate Submission format
     */
    private fun populateSubmissionFormat(sBaseURL: String, sToken: String) {
        mastersViewModel.populateKmFormatTypes(
            url = sBaseURL + MethodConstants.POPULATE_MASTER_LIST,
            sAuthorization = sToken,
            sTableName = MASTERS_KNOWLEDGE_MANAGEMENT_FORMAT_TYPES
        )
        mastersViewModel.populateKmFormatTypesMutableData.observe(viewLifecycleOwner) {
            formatTypeItems = it
        }
    }

    private fun showFormatTypesDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.submission_category)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = KM_FormatTypes(
                formatTypeItems,
                KM_FormatTypes.OnItemClick {
                    cboSubmissionFormat!!.text = it.sName
                    iSubmissionFormatID = it.id
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
                sErrorSource = KMPostRepositoryInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }
}