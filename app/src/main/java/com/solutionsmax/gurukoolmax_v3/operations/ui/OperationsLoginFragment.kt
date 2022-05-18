package com.solutionsmax.gurukoolmax_v3.operations.ui

import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.MANAGEMENT_LOGIN
import com.solutionsmax.gurukoolmax_v3.core.common.PortalIdConstants
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.data.mapper.toItemList
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Event
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.ErrorLogsViewModel
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.TokenViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentOperationsLoginBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.LicenseViewModel
import com.solutionsmax.gurukoolmax_v3.room.entity.LicenseItem
import javax.inject.Inject

class OperationsLoginFragment : BaseFragment() {

    private lateinit var binding: FragmentOperationsLoginBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var operationsViewModel: OperationsViewModel
    private lateinit var licenseViewModel: LicenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOperationsLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tokenViewModel = ViewModelProvider(this, viewModelFactory)[TokenViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]
        operationsViewModel =
            ViewModelProvider(this, viewModelFactory)[OperationsViewModel::class.java]
        licenseViewModel =
            ViewModelProvider(this, viewModelFactory)[LicenseViewModel::class.java]

        tokenViewModel.retrieveTokensFromLocal()
        setUpObservers()

        binding.btnLogin.setOnClickListener {
            if (TextUtils.isEmpty(binding.txtUsername.text) || TextUtils.isEmpty(binding.txtPassword.text)
                || TextUtils.isEmpty(binding.txtSchoolCode.text)
            ) {
                showError(
                    getString(R.string.enter_credentials),
                    getString(R.string.enter_credentials_desc)
                )
            } else {
                // Get Base URL based on the SITE code provided
                getLicenseInfo()
            }
        }

        binding.imgBack.setOnClickListener {
            currentNavController.navigate(R.id.mainMenuFragment)
        }

        binding.lblResetSchoolCode.setOnClickListener {
            binding.txtSchoolCode.setText("")
            licenseViewModel.deleteLicense()
        }

        binding.passwordHide.setOnClickListener {
            if (!TextUtils.isEmpty(binding.txtPassword.text)) {
                binding.passwordShow.visibility = View.VISIBLE
                binding.passwordHide.visibility = View.GONE
                binding.txtPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }

        binding.passwordShow.setOnClickListener {
            if (!TextUtils.isEmpty(binding.txtPassword.text)) {
                binding.passwordShow.visibility = View.GONE
                binding.passwordHide.visibility = View.VISIBLE
                binding.txtPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
        }
    }

    private fun getLicenseInfo() {
        licenseViewModel.retrieveSiteInfoBySideCode(
            sToken,
            1,
            binding.txtSchoolCode.text.toString()
        )
        validateLogin()
    }

    private fun setUpObservers() {
        tokenViewModel.retrieveTokenLiveData.observe(viewLifecycleOwner) {
            sToken = it.first().access_token
        }
        licenseViewModel.retrieveLicenseInfo()
        with(licenseViewModel) {
            retrieveLicenseInfoUseCase.observe(viewLifecycleOwner) { license ->
                if (license.isNotEmpty()) {
                    binding.txtSchoolCode.setText(license.first().site_code)
                    binding.lblResetSchoolCode.visibility = View.VISIBLE
                    validate(license)
                } else {
                    binding.lblResetSchoolCode.visibility = View.GONE
                    retrieveInfoBySideCodeLiveData.observe(viewLifecycleOwner) {
                        errorLiveData.observe(viewLifecycleOwner) { error ->
                            showError(error.peekContent())
                        }
                        validate(it.first().toItemList())
                    }
                }
            }
        }
    }

    private fun validate(license: List<LicenseItem>) {
        if (license.isNullOrEmpty()) {
            showError(
                title = getString(R.string.site_code_error),
                message = getString(R.string.site_code_error_desc)
            )
            binding.txtSchoolCode.setText("")
        } else {
            sBaseURL = license.first().rest_url
            if (sBaseURL.isEmpty()) {
                showError(
                    title = getString(R.string.site_code_error),
                    message = getString(R.string.site_code_error_desc)
                )
                binding.txtSchoolCode.setText("")
            } else {
                with(operationsViewModel) {
                    errorLiveData.observe(viewLifecycleOwner) { error ->
                        showError(error.peekContent())
                        with(errorLogsViewModel) {
                            postErrors(error)
                            postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                        }
                    }
                    validateOperationsLoginMutableData.observe(viewLifecycleOwner) { validateLogin ->
                        if (validateLogin > 0) {
                            binding.progressBar.visibility = View.INVISIBLE
                            currentNavController.navigate(R.id.operationsMenuFragment)
                        } else {
                            binding.progressBar.visibility = View.INVISIBLE
                            showError(
                                getString(R.string.invlaid_credentials),
                                getString(R.string.invlaid_credentials_desc)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun validateLogin() {
        binding.progressBar.visibility = View.VISIBLE

        operationsViewModel.validateOperationsLogin(
            url = sBaseURL + MANAGEMENT_LOGIN,
            sAuthorization = sToken,
            iGroupID = 1,
            iSchoolID = 1,
            sUsername = binding.txtUsername.text.toString(),
            sPassword = binding.txtPassword.text.toString()
        )
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
                sErrorSource = OperationsLoginFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

}