package com.solutionsmax.gurukoolmax_v3.operations.ui.on_board_attendance

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_STUDENT_ATTENDANCE_CHECK_ATTENDANCE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_STUDENT_ATTENDANCE_CHECK_DUPLICATE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_STUDENT_ATTENDANCE_POST_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.STUDENT_CHECK_VALID_ADMISSION_NUMBER
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentOnBoardAttendanceBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.on_board_attendance.OnBoardAttendancePostItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.on_board_attendance.OnBoardPostFleetAttendanceParams
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class OnBoardAttendanceFragment : BaseFragment() {

    private lateinit var binding: FragmentOnBoardAttendanceBinding
    private lateinit var codeScannerView: CodeScanner

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var onBoardAttendanceViewModel: OnBoardAttendanceViewModel
    private var sScannedValue: String = ""
    private var iBusRouteID: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardAttendanceBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iBusRouteID = requireArguments().getInt("id", -1)

        binding.toolbar.apply {
            title = getString(R.string.on_board_attendance)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { currentNavController.navigate(R.id.operationsMenuFragment) }
        }

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        onBoardAttendanceViewModel =
            ViewModelProvider(this, viewModelFactory)[OnBoardAttendanceViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setupObservers()

        codeScannerView = CodeScanner(requireActivity(), binding.scannerView)
        codeScannerView.decodeCallback = DecodeCallback {
            activity?.runOnUiThread {
                sScannedValue = it.text
                onBoardAttendanceViewModel.checkValidAdmissionNumber(
                    url = sBaseURL + STUDENT_CHECK_VALID_ADMISSION_NUMBER,
                    sAuthorization = sToken,
                    iGroupID = iGroupID,
                    iSchoolID = iBranchID,
                    sAdmissionNum = sScannedValue
                )
            }
        }
        binding.scannerView.setOnClickListener {
            codeScannerView.startPreview()
        }
    }

    private fun setupObservers() {
        tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
            sBaseURL = it.sBaseURL
            sToken = it.sToken
            iGroupID = it.iGroupID
            iBranchID = it.iBranchID
        }

        with(onBoardAttendanceViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
            }
            checkValidAdmissionNumberMutableData.observe(viewLifecycleOwner) {
                if (it > 0) {
                    checkStudentAttendanceValid()
                    checkStudentAttendanceMutableData.observe(viewLifecycleOwner) { validStudentAttendance ->
                        if (validStudentAttendance > 0) {
                            checkDuplicateAttendanceScanned()
                            checkDuplicateStudentAttendanceMutableData.observe(viewLifecycleOwner) { duplicateStudentAttendance ->
                                if (duplicateStudentAttendance > 0) {
                                    postStudentAttendanceFleet()
                                    postFleetStudentAttendanceMutableData.observe(viewLifecycleOwner) { postItem ->
                                        if (postItem > 0) {
                                            codeScannerView.startPreview()
                                            currentNavController.navigate(R.id.mainMenuFragment)
                                        } else {
                                            showError(
                                                title = getString(R.string.something_went_wrong),
                                                message = getString(R.string.could_not_save_info)
                                            )
                                        }
                                    }
                                } else {
                                    showError(
                                        title = getString(R.string.duplicate_student_attendance),
                                        message = getString(R.string.duplicate_student_attendance_desc)
                                    )
                                }
                            }
                        } else {
                            showError(
                                title = getString(R.string.invalid_student_attendance),
                                message = getString(R.string.invalid_student_attendance_desc)
                            )
                        }
                    }
                } else {
                    showError(
                        title = getString(R.string.invalid_code_scanned),
                        message = getString(R.string.invalid_code_scanned_desc)
                    )
                }
            }
        }
    }

    private fun postStudentAttendanceFleet() {
        val onBoardAttendancePostItem = OnBoardAttendancePostItem(
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            sBarcodeSerial = sScannedValue,
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
                url = sBaseURL + FLEET_STUDENT_ATTENDANCE_POST_INFO,
                sAuthorization = sToken,
                onBoardAttendancePostItem = onBoardAttendancePostItem
            )
        )
    }

    private fun checkDuplicateAttendanceScanned() {
        onBoardAttendanceViewModel.checkDuplicateStudentAttendance(
            url = sBaseURL + FLEET_STUDENT_ATTENDANCE_CHECK_DUPLICATE,
            sAuthorization = sToken,
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            iRouteID = iBusRouteID,
            iStudentID = -1,
            dDateOfTravel = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext())
        )
    }

    private fun checkStudentAttendanceValid() {
        onBoardAttendanceViewModel.checkStudentAttendance(
            url = sBaseURL + FLEET_STUDENT_ATTENDANCE_CHECK_ATTENDANCE,
            sAuthorization = sToken,
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            sAdmissionNum = sScannedValue,
            iRouteID = 1,
            iStudentID = -1,
            dDateOfTravel = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext())
        )
    }

    override fun onStart() {
        super.onStart()
        codeScannerView.startPreview()
    }

    override fun onPause() {
        codeScannerView.releaseResources()
        super.onPause()
    }
}