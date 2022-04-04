package com.solutionsmax.gurukoolmax_v3.operations.ui.on_board_attendance

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.STUDENT_CHECK_VALID_ADMISSION_NUMBER
import com.solutionsmax.gurukoolmax_v3.core.common.PortalIdConstants
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Event
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.ErrorLogsViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentOnBoardManualAttendanceBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.PopulateFleetBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.on_board_attendance.OnBoardAttendancePostItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.on_board_attendance.OnBoardPostFleetAttendanceParams
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register.RegisteredFleetViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class OnBoardManualAttendanceFragment : BaseFragment() {

    private lateinit var binding: FragmentOnBoardManualAttendanceBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var registeredFleetViewModel: RegisteredFleetViewModel
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var onBoardAttendanceViewModel: OnBoardAttendanceViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel

    private var iBusRouteID: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardManualAttendanceBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = getString(R.string.on_board_attendance)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            val bundle = bundleOf("menu" to OperationMenuConstants.ON_BOARD_ATTENDANCE)
            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.operationsSubMenuFragment,
                    bundle
                )
            }
        }

        registeredFleetViewModel =
            ViewModelProvider(this, viewModelFactory)[RegisteredFleetViewModel::class.java]
        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        onBoardAttendanceViewModel =
            ViewModelProvider(this, viewModelFactory)[OnBoardAttendanceViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setupObservers()

        binding.btnSubmit.setOnClickListener {
            if (iBusRouteID == -1 || TextUtils.isEmpty(binding.txtAdmissionNumber.text)) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                onBoardAttendanceViewModel.checkValidAdmissionNumber(
                    url = sBaseURL + STUDENT_CHECK_VALID_ADMISSION_NUMBER,
                    sAuthorization = sToken,
                    iGroupID = iGroupID,
                    iSchoolID = iBranchID,
                    sAdmissionNum = binding.txtAdmissionNumber.text.toString()
                )
            }
        }

    }

    private fun setupObservers() {
        tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
            sBaseURL = it.sBaseURL
            sToken = it.sToken
            iGroupID = it.iGroupID
            iBranchID = it.iBranchID
            populateFleetBusRoute(sBaseURL, sToken)
        }

        with(registeredFleetViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
            }
        }

        with(onBoardAttendanceViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
            }
            checkValidAdmissionNumberMutableData.observe(viewLifecycleOwner) {
                if (it > 0) {
                    // Check if the code scanned is valid
                    checkStudentAttendanceValid()
                    checkStudentAttendanceMutableData.observe(viewLifecycleOwner) { validStudentAttendance ->
                        if (validStudentAttendance > 0) {
                            // check if the code scanned is duplicate
                            checkDuplicateAttendanceScanned()
                            checkDuplicateStudentAttendanceMutableData.observe(viewLifecycleOwner) { duplicateStudentAttendance ->
                                if (duplicateStudentAttendance > 0) {
                                    postStudentAttendanceFleet()
                                    postFleetStudentAttendanceMutableData.observe(viewLifecycleOwner) { postItem ->
                                        if (postItem > 0) {
                                            binding.txtAdmissionNumber.setText("")
                                        } else {
                                            showError(
                                                title = getString(R.string.something_went_wrong),
                                                message = getString(R.string.could_not_save_info)
                                            )
                                        }
                                    }
                                } else {
                                    showError(
                                        title = getString(R.string.duplicate_student_attendance_admission),
                                        message = getString(R.string.duplicate_student_attendance_admission_desc)
                                    )
                                }
                            }
                        } else {
                            showError(
                                title = getString(R.string.invalid_student_attendance_admission),
                                message = getString(R.string.invalid_student_attendance_admission_desc)
                            )
                        }
                    }
                } else {
                    showError(
                        title = getString(R.string.invalid_student_attendance_admission),
                        message = getString(R.string.invalid_code_scanned_admission_desc)
                    )
                }
            }
        }
    }

    private fun populateFleetBusRoute(sBaseURL: String, sToken: String) {
        with(registeredFleetViewModel) {
            populateFleetRoutesList(
                url = sBaseURL + MethodConstants.FLEET_POPULATE_BUS_ROUTES,
                sAuthorization = sToken,
                iGroupID = iGroupID,
                iSchoolID = iBranchID,
                iStatusID = -1
            )
            populateFleetRoutesMutableData.observe(viewLifecycleOwner) {
                binding.cboRoute.apply {
                    it.add(0, PopulateFleetBusRoutesItems(-1, getString(R.string.choose_an_option)))
                    adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, it)
                    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            p2: Int,
                            p3: Long
                        ) {
                            if (p2 > 0) {
                                val busRoute =
                                    binding.cboRoute.selectedItem as PopulateFleetBusRoutesItems
                                iBusRouteID = busRoute.id
                            }
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }
                    }
                }
            }
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
                sErrorSource = OnBoardAttendanceFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

    private fun postStudentAttendanceFleet() {
        val onBoardAttendancePostItem = OnBoardAttendancePostItem(
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            sBarcodeSerial = binding.txtAdmissionNumber.text.toString(),
            iRouteID = iBusRouteID,
            iStudentID = -1,
            sDateOfTravel = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext()),
            iWorkflowStatusId = 1,
            sCreateDate = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext()),
            sUpdateDate = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext())

        )
        onBoardAttendanceViewModel.postFleetStudentAttendance(
            OnBoardPostFleetAttendanceParams(
                url = sBaseURL + MethodConstants.FLEET_STUDENT_ATTENDANCE_POST_INFO,
                sAuthorization = sToken,
                onBoardAttendancePostItem = onBoardAttendancePostItem
            )
        )
    }

    private fun checkDuplicateAttendanceScanned() {
        onBoardAttendanceViewModel.checkDuplicateAttendanceByAdmissionNum(
            url = sBaseURL + MethodConstants.FLEET_STUDENT_ATTENDANCE_CHECK_DUPLICATE_BY_ADMISSION_NUMBER,
            sAuthorization = sToken,
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            iRouteID = iBusRouteID,
            sAdmissionNum = binding.txtAdmissionNumber.text.toString(),
            dDateOfTravel = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext())
        )
    }

    private fun checkStudentAttendanceValid() {
        onBoardAttendanceViewModel.checkStudentAttendance(
            url = sBaseURL + MethodConstants.FLEET_STUDENT_ATTENDANCE_CHECK_ATTENDANCE,
            sAuthorization = sToken,
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            sAdmissionNum = binding.txtAdmissionNumber.text.toString(),
            iRouteID = iBusRouteID,
            iStudentID = -1,
            dDateOfTravel = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext())
        )
    }

}