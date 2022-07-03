package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus

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
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.PostCurriculumInfoItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectList
import com.solutionsmax.gurukoolmax_v3.academics.ui.AcademicsViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.SubjectManagementViewModel
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus.spinner_adapter.CS_AcademicBoard
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus.spinner_adapter.CS_Class
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus.spinner_adapter.CS_Subject
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
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentCurriculumSetupInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class CurriculumSetupInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentCurriculumSetupInfoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var academicsViewModel: AcademicsViewModel
    private lateinit var subjectManagementViewModel: SubjectManagementViewModel
    private lateinit var curriculumInfoViewModel: CurriculumInfoViewModel

    companion object {
        var cboAcademicBoard: TextView? = null
        var iAcademicBoardID = -1
        var cboClass: TextView? = null
        var iClassID = -1
        var cboSubject: TextView? = null
        var iSubjectID = -1
        var dialog: Dialog? = null
    }

    private lateinit var boardItems: List<PopulateMasterListItem>
    private lateinit var classItems: List<PopulateClassItems>
    private lateinit var populateSubjectList: List<PopulateSubjectList>

    private var iEditID = -1
    private var sClassMethod = ""
    private var iSettingsID = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCurriculumSetupInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = getString(R.string.course_curricilum_setup_list)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.subjectManagementListFragment
                )
            }
        }

        iEditID = requireArguments().getInt("id", -1)

        cboAcademicBoard = view.findViewById(R.id.cboAcademicBoard)
        cboClass = view.findViewById(R.id.cboClass)
        cboSubject = view.findViewById(R.id.cboSubject)

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

        binding.btnSubmit.setOnClickListener {
            if (iAcademicBoardID == -1 || iClassID == -1 || iSubjectID == -1 ||
                TextUtils.isEmpty(binding.txtSectionTitle.text) ||
                TextUtils.isEmpty(binding.txtFocus.text) ||
                TextUtils.isEmpty(binding.txtDuration.text) ||
                TextUtils.isEmpty(binding.txtSyllabusDetails.text)
            ) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                binding.progressBar.visibility = View.VISIBLE
                curriculumInfoViewModel.checkDuplicateCurriculumInfo(
                    CheckDuplicateCurriculumInfoParams(
                        url = sBaseURL,
                        sAuthorization = sToken,
                        iGroupID = 1,
                        iSchoolID = 1,
                        iBoardID = iAcademicBoardID,
                        iGradeID = iClassID,
                        sSectionTitle = binding.txtSectionTitle.text.toString(),
                        iSubjectID = iSubjectID
                    )
                )
            }
        }

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        settingsViewModel.retrieveSettings()
        setUpObservers()

        cboAcademicBoard!!.setOnClickListener {
            showBoardDialog()
        }

        cboClass!!.setOnClickListener {
            if (iAcademicBoardID > 0) {
                if (iSettingsID == 1) {
                    populateClass(iAcademicBoardID)
                } else {
                    populateSemesterClass(iAcademicBoardID)
                }
                if (::classItems.isInitialized) {
                    showClassDialog()
                }
            }
        }

        cboSubject!!.setOnClickListener {
            if (::populateSubjectList.isInitialized) {
                showSubjectDialog()
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
        }
        with(curriculumInfoViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(error = it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
            }
            curriculumInfoViewModel.retrieveMutableCurriculumDetails.observe(viewLifecycleOwner) {
                for (details in it) {
                    iAcademicBoardID = details.iAcademicBoardID
                    cboAcademicBoard!!.text = details.sAcademicBoard
                    iClassID = details.iClassGradeId
                    cboClass!!.text = details.sClassGrade
                    iSubjectID = details.iSubjectID
                    cboSubject!!.text = details.sSubjectName
                    binding.txtSyllabusDetails.setText(details.sSyllabusDetails)
                    binding.txtSectionTitle.setText(details.sSectionTitle)
                    binding.txtFocus.setText(details.iMarkFocus.toString())
                    binding.txtDuration.setText(details.iScheduledDuration.toString())
                    binding.txtSyllabusDetails.setText(details.sSyllabusDetails)
                }
            }
            curriculumInfoViewModel.checkDuplicateMutableCurriculumInfo.observe(viewLifecycleOwner) { duplicate ->
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
            curriculumInfoViewModel.postMutableCurriculumInfo.observe(viewLifecycleOwner) { post ->
                if (post > 0) {
                    currentNavController.navigate(R.id.curriculumSetupListFragment)
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                    showError(
                        getString(R.string.could_not_save_info),
                        getString(R.string.could_not_save_info_desc)
                    )
                }
            }
            curriculumInfoViewModel.amendMutableCurriculumInfo.observe(viewLifecycleOwner) { amend ->
                if (amend > 0) {
                    currentNavController.navigate(R.id.curriculumSetupListFragment)
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

    /**
     * Post Info
     */
    private fun postInfo() {
        curriculumInfoViewModel.postCurriculumInfo(
            PostCurriculumInfoParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postCurriculumInfoItem = PostCurriculumInfoItem(
                    iGroupID = 1,
                    iSchoolID = 1,
                    iAcademicBoardID = iAcademicBoardID,
                    iClassGradeID = iClassID,
                    iSubjectID = iSubjectID,
                    sSectionTitle = binding.txtSectionTitle.text.toString(),
                    sSyllabusDetails = binding.txtSyllabusDetails.text.toString(),
                    iMarksFocus = Integer.parseInt(binding.txtFocus.text.toString()),
                    iScheduleDuration = Integer.parseInt(binding.txtDuration.text.toString()),
                    iLecturesCount = -1,
                    iTutorialCount = -1,
                    iExtendedTutorialCount = -1,
                    iPracticalCount = -1,
                    iProjectCount = -1,
                    iOutsideClassRoomCount = -1,
                    iTotalCreditCount = -1,
                    iSortOrder = -1,
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
        curriculumInfoViewModel.amendCurriculumInfo(
            PostCurriculumInfoParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postCurriculumInfoItem = PostCurriculumInfoItem(
                    id = iEditID,
                    iGroupID = 1,
                    iSchoolID = 1,
                    iAcademicBoardID = iAcademicBoardID,
                    iClassGradeID = iClassID,
                    iSubjectID = iSubjectID,
                    sSectionTitle = binding.txtSectionTitle.text.toString(),
                    sSyllabusDetails = binding.txtSyllabusDetails.text.toString(),
                    iMarksFocus = Integer.parseInt(binding.txtFocus.text.toString()),
                    iScheduleDuration = Integer.parseInt(binding.txtDuration.text.toString()),
                    iLecturesCount = -1,
                    iTutorialCount = -1,
                    iExtendedTutorialCount = -1,
                    iPracticalCount = -1,
                    iProjectCount = -1,
                    iOutsideClassRoomCount = -1,
                    iTotalCreditCount = -1,
                    iSortOrder = -1,
                    iWorkflowStatusID = 1,
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    private fun retrieveDetails(iEditID: Int) {
        curriculumInfoViewModel.retrieveCurriculumDetails(
            RetrieveCurriculumDetailsParams(
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
            adapter = CS_AcademicBoard(
                boardItems,
                CS_AcademicBoard.OnItemClick {
                    cboAcademicBoard!!.text = it.sName
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
                adapter = CS_Class(
                    classItems,
                    CS_Class.OnItemClick {
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
            adapter = CS_Subject(
                populateSubjectList,
                CS_Subject.OnItemClick {
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
                sErrorSource = CurriculumSetupInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

}