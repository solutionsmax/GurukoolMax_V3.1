package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_REGISTRATION_AMEND_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_REGISTRATION_CHECK_DUPLICATE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_REGISTRATION_POST_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_REGISTRATION_RETRIEVE_DETAILS
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.TokenViewModel
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentRegisteredFleetInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetRegisterPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_register.FleetRegisterPostParams
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.LicenseViewModel
import javax.inject.Inject


class RegisteredFleetInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisteredFleetInfoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var licenseViewModel: LicenseViewModel
    private lateinit var registeredFleetViewModel: RegisteredFleetViewModel
    private var iEditID = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisteredFleetInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = getString(R.string.register_fleet)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { currentNavController.navigate(R.id.registeredFleetListFragment) }
        }

        iEditID = requireArguments().getInt("id", -1)

        tokenViewModel = ViewModelProvider(this, viewModelFactory)[TokenViewModel::class.java]
        licenseViewModel = ViewModelProvider(this, viewModelFactory)[LicenseViewModel::class.java]
        registeredFleetViewModel =
            ViewModelProvider(this, viewModelFactory)[RegisteredFleetViewModel::class.java]

        tokenViewModel.retrieveTokensFromLocal()
        tokenViewModel.retrieveTokenLiveData.observe(viewLifecycleOwner) { token ->
            sToken = token.first().access_token
            licenseViewModel.retrieveLicenseInfo()
            licenseViewModel.retrieveLicenseInfoUseCase.observe(viewLifecycleOwner) { license ->
                sBaseURL = license.first().rest_url
                if (iEditID > 0) {
                    binding.btnSubmit.text = getString(R.string.edit)
                    retrieveDetails(iEditID)
                }
            }
        }

        binding.btnSubmit.setOnClickListener {
            if (TextUtils.isEmpty(binding.txtVehicleName.text) || TextUtils.isEmpty(binding.txtRegistration.text) ||
                TextUtils.isEmpty(binding.txtModel.text) || TextUtils.isEmpty(binding.txtMake.text)
            ) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                registeredFleetViewModel.checkRegisteredFleetDuplicateInfo(
                    sBaseURL + FLEET_REGISTRATION_CHECK_DUPLICATE,
                    sToken,
                    binding.txtRegistration.text.toString()
                )
                registeredFleetViewModel.checkRegisteredFleetDuplicateMutableData.observe(
                    viewLifecycleOwner
                ) { duplicate ->
                    if (iEditID > 0) {
                        if (duplicate > 0) {
                            if (duplicate == iEditID) {
                                amendInfo()
                            } else {
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
                            showError(
                                getString(R.string.duplicate_info),
                                getString(R.string.duplicate_info_desc)
                            )
                        } else {
                            postInfo()
                        }
                    }
                }
            }
        }
    }

    /**
     * Retrieve Details
     */
    private fun retrieveDetails(iEditID: Int) {
        registeredFleetViewModel.retrieveRegisteredFleetDetails(
            url = sBaseURL + FLEET_REGISTRATION_RETRIEVE_DETAILS,
            sAuthorization = sToken,
            id = iEditID
        )
        registeredFleetViewModel.retrieveRegisteredFleetDetailsMutableData.observe(
            viewLifecycleOwner
        ) {
            if (it.isNotEmpty()) {
                binding.txtVehicleName.setText(it.first().sVehicleName)
                binding.txtRegistration.setText(it.first().sRegistrationNumber)
                binding.txtModel.setText(it.first().sModel)
                binding.txtMake.setText(it.first().sMake)
            } else {
                showError(
                    getString(R.string.something_went_wrong),
                    getString(R.string.something_went_wrong_desc)
                )
            }
        }
    }

    /**
     * Post Info
     */
    private fun postInfo() {
        val postInfo = FleetRegisterPostInfoItem(
            iGroupID = 1,
            iBranchID = 1,
            sVehicleName = binding.txtVehicleName.text.toString(),
            sRegistrationNumber = binding.txtRegistration.text.toString(),
            sVinNumber = binding.txtVin.text.toString(),
            sPurchaseDate = "-1",
            sModel = binding.txtModel.text.toString(),
            sMake = binding.txtMake.text.toString(),
            iManufactureYearID = -1,
            iFuelTypeID = -1,
            sColor = "-1",
            sCubicCapacity = "-1",
            iSeatingCapacity = -1,
            sRegistrationAuthority = "-1",
            sPhotoRef = "-1",
            sPhotoURL = "-1",
            iUserID = -1,
            iWorkflowStatusID = -1,
            sCreateDate = "-1",
            sUpdateDate = "-1"
        )
        val postParamsInfo = FleetRegisterPostParams(
            sBaseURL + FLEET_REGISTRATION_POST_INFO,
            sToken,
            postInfo
        )
        registeredFleetViewModel.postRegisteredFleetInfo(postParamsInfo)
        registeredFleetViewModel.postRegisteredFleetMutableData.observe(viewLifecycleOwner) {
            if (it > 0) {
                currentNavController.navigate(R.id.registeredFleetListFragment)
            } else {
                showError(
                    getString(R.string.could_not_save_info),
                    getString(R.string.could_not_save_info_desc)
                )
            }
        }
    }

    /**
     * Amend Info
     */
    private fun amendInfo() {
        val amendInfo = FleetRegisterPostInfoItem(
            id = iEditID,
            iGroupID = 1,
            iBranchID = 1,
            sVehicleName = binding.txtVehicleName.text.toString(),
            sRegistrationNumber = binding.txtRegistration.text.toString(),
            sVinNumber = binding.txtVin.text.toString(),
            sPurchaseDate = "-1",
            sModel = binding.txtModel.text.toString(),
            sMake = binding.txtMake.text.toString(),
            iManufactureYearID = -1,
            iFuelTypeID = -1,
            sColor = "-1",
            sCubicCapacity = "-1",
            iSeatingCapacity = -1,
            sRegistrationAuthority = "-1",
            sPhotoRef = "-1",
            sPhotoURL = "-1",
            iUserID = -1,
            iWorkflowStatusID = -1,
            sCreateDate = "-1",
            sUpdateDate = "-1"
        )
        val amendParamsInfo = FleetRegisterPostParams(
            sBaseURL + FLEET_REGISTRATION_AMEND_INFO,
            sToken,
            amendInfo
        )
        registeredFleetViewModel.amendRegisteredFleetInfo(amendParamsInfo)
        registeredFleetViewModel.amendRegisteredFleetMutableData.observe(viewLifecycleOwner) {
            if (it > 0) {
                currentNavController.navigate(R.id.registeredFleetList)
            } else {
                showError(
                    getString(R.string.could_not_save_info),
                    getString(R.string.could_not_save_info_desc)
                )
            }
        }
    }
}