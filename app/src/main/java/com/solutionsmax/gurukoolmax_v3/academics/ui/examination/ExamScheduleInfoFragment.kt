package com.solutionsmax.gurukoolmax_v3.academics.ui.examination

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
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.PostExamScheduleItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.CheckDuplicateExamScheduleParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PostExamScheduleParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.RetrieveDetailsExamScheduleParams
import com.solutionsmax.gurukoolmax_v3.academics.ui.examination.spinner_adapter.ES_Board
import com.solutionsmax.gurukoolmax_v3.academics.ui.examination.spinner_adapter.ES_Year
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
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentExamScheduleInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class ExamScheduleInfoFragment : BaseFragment() {
    private lateinit var binding: FragmentExamScheduleInfoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var examinationScheduleViewModel: ExaminationScheduleViewModel

    companion object {
        var cboAcademicYear: TextView? = null
        var iAcademicYearID: Int = -1
        var cboAcademicBoard: TextView? = null
        var iAcademicBoard: Int = -1
        var dialog: Dialog? = null
    }

    private var boardItems: List<PopulateMasterListItem> = emptyList()
    private var academicYearList: List<PopulateMasterListItem> = emptyList()

    private var iEditID = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentExamScheduleInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            title = getString(R.string.examination_schedule)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.examScheduleListFragment
                )
            }
        }
        iEditID = requireArguments().getInt("id", -1)

        cboAcademicYear = view.findViewById(R.id.cboAcademicYear)
        cboAcademicBoard = view.findViewById(R.id.cboBoard)

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]
        mastersViewModel = ViewModelProvider(this, viewModelFactory)[MastersViewModel::class.java]
        examinationScheduleViewModel =
            ViewModelProvider(this, viewModelFactory)[ExaminationScheduleViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setUpObservers()

        cboAcademicBoard!!.setOnClickListener {
            showBoardDialog()
        }

        cboAcademicYear!!.setOnClickListener {
            showAcademicYearDialog()
        }

        binding.btnSubmit.setOnClickListener {
            if (iAcademicBoard == -1 || iAcademicYearID == -1 || TextUtils.isEmpty(binding.txtExaminationName.text)) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                examinationScheduleViewModel.checkDuplicateExamSchedule(
                    CheckDuplicateExamScheduleParams(
                        url = sBaseURL,
                        sAuthorization = sToken,
                        iGroupID = 1,
                        iSchoolID = 1,
                        iBoardID = iAcademicBoard,
                        iYearID = iAcademicYearID,
                        sScheduleName = binding.txtExaminationName.text.toString()
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
            tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
                sBaseURL = it.sBaseURL
                sToken = it.sToken

                populateAcademicYear(sBaseURL, sToken)
                populateAcademicBoard(sBaseURL, sToken)

                if (iEditID > 0) {
                    retrieveDetails(iEditID)
                }
            }
            with(examinationScheduleViewModel) {
                retrieveDetailsMutableExamSchedule.observe(viewLifecycleOwner) { details ->
                    for (items in details) {
                        cboAcademicBoard!!.text = items.sAcademicBoard
                        iAcademicBoard = items.iAcademicBoardID
                        cboAcademicYear!!.text = items.sCalendarYear
                        iAcademicYearID = items.iCalendarYearID
                        binding.txtExaminationName.setText(items.sScheduleName)
                        binding.txtRemarks.setText(items.sNotes)
                        populateAcademicYear(sBaseURL, sToken)
                        populateAcademicBoard(sBaseURL, sToken)
                    }
                }
                checkDuplicateMutableExamSchedule.observe(viewLifecycleOwner) { duplicate ->
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
                postMutableExamSchedule.observe(viewLifecycleOwner) { post ->
                    if (post > 0) {
                        currentNavController.navigate(R.id.examScheduleListFragment)
                    } else {
                        showError(
                            getString(R.string.could_not_save_info),
                            getString(R.string.could_not_save_info_desc)
                        )
                    }
                }
                amendMutableExamSchedule.observe(viewLifecycleOwner) { amend ->
                    if (amend > 0) {
                        currentNavController.navigate(R.id.examScheduleListFragment)
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
        examinationScheduleViewModel.postExamSchedule(
            PostExamScheduleParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postExamScheduleItem = PostExamScheduleItem(
                    iGroupID = 1,
                    iSchoolID = 1,
                    iAcademicBoardID = iAcademicBoard,
                    iCalendarYearID = iAcademicYearID,
                    sScheduleName = binding.txtExaminationName.text.toString(),
                    sNotes = binding.txtRemarks.text.toString(),
                    iWorkflowStatusID = 1,
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    private fun amendInfo() {
        examinationScheduleViewModel.amendExamSchedule(
            PostExamScheduleParams(
                url = sBaseURL,
                sAuthorization = sToken,
                postExamScheduleItem = PostExamScheduleItem(
                    id = iEditID,
                    iGroupID = 1,
                    iSchoolID = 1,
                    iAcademicBoardID = iAcademicBoard,
                    iCalendarYearID = iAcademicYearID,
                    sScheduleName = binding.txtExaminationName.text.toString(),
                    sNotes = binding.txtRemarks.text.toString(),
                    iWorkflowStatusID = 1,
                    sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
                    sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
                )
            )
        )
    }

    private fun retrieveDetails(iEditID: Int) {
        examinationScheduleViewModel.retrieveExamScheduleDetails(
            RetrieveDetailsExamScheduleParams(
                url = sBaseURL,
                sAuthorization = sToken,
                id = iEditID
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
            adapter = ES_Year(
                academicYearList,
                ES_Year.OnItemClick {
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
            adapter = ES_Board(
                boardItems,
                ES_Board.OnItemClick {
                    cboAcademicBoard!!.text = it.sName
                    iAcademicBoard = it.id
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
                sErrorSource = ExamScheduleInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

}