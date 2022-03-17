package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.movement

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_MOVEMENT_AMEND_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_MOVEMENT_CHECK_DUPLICATE_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_MOVEMENT_POST_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_MOVEMENT_RETRIEVE_DETAILS
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_REGISTRATION_POPULATE_LIST
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.TokenViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentRegisteredFleetMovementInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.PopulateRegisteredFleetList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_movement.FleetMovementPostParams
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register.RegisteredFleetViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.LicenseViewModel
import javax.inject.Inject

class RegisteredFleetMovementInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisteredFleetMovementInfoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var licenseViewModel: LicenseViewModel
    private lateinit var fleetRegisterViewModel: RegisteredFleetViewModel
    private lateinit var fleetMovementViewModel: FleetMovementViewModel
    private var iVehicleID: Int = -1
    private var iEditID: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisteredFleetMovementInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            title = getString(R.string.fleet_movement)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { currentNavController.navigate(R.id.registeredFleetMovementListFragment) }
        }

        iEditID = requireArguments().getInt("id", -1)
        binding.lblDateOfRecord.text = DateUtils.todayDateTime().getMediumDateFormat(requireContext())

        tokenViewModel = ViewModelProvider(this, viewModelFactory)[TokenViewModel::class.java]
        licenseViewModel = ViewModelProvider(this, viewModelFactory)[LicenseViewModel::class.java]
        fleetMovementViewModel =
            ViewModelProvider(this, viewModelFactory)[FleetMovementViewModel::class.java]
        fleetRegisterViewModel =
            ViewModelProvider(this, viewModelFactory)[RegisteredFleetViewModel::class.java]

        tokenViewModel.retrieveTokensFromLocal()
        tokenViewModel.retrieveTokenLiveData.observe(viewLifecycleOwner) { token ->
            licenseViewModel.retrieveLicenseInfo()
            licenseViewModel.retrieveLicenseInfoUseCase.observe(viewLifecycleOwner) { license ->
                iGroupID = license.first().group_id
                iBranchID = license.first().branch_id
                sToken = token.first().access_token
                sBaseURL = license.first().rest_url
                if (iEditID > 0) {
                    binding.btnSubmit.text = getString(R.string.edit)
                    retrieveDetails(iEditID)
                }
                fleetRegisterViewModel.populateRegisteredFleetList(
                    license.first().rest_url + FLEET_REGISTRATION_POPULATE_LIST,
                    token.first().access_token, 1
                )
                fleetRegisterViewModel.populateRegisteredFleetMutableData.observe(viewLifecycleOwner) {
                    binding.cboVehicleName.apply {
                        it.add(0, PopulateRegisteredFleetList(-1, getString(R.string.choose_an_option)))
                        adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, it)
                        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                p0: AdapterView<*>?,
                                p1: View?,
                                p2: Int,
                                p3: Long
                            ) {
                                if (p2 > 0) {
                                    val vehicleItem =
                                        binding.cboVehicleName.selectedItem as PopulateRegisteredFleetList
                                    iVehicleID = vehicleItem.id
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

        binding.btnSubmit.setOnClickListener {
            if (TextUtils.isEmpty(binding.txtOpeningRecord.text) || TextUtils.isEmpty(binding.txtClosingReading.text) ||
                TextUtils.isEmpty(binding.txtTripReading.text) || TextUtils.isEmpty(binding.txtFleetNumber.text) ||
                TextUtils.isEmpty(binding.txtFuelCoupon.text) || TextUtils.isEmpty(binding.txtFuelLiters.text) ||
                TextUtils.isEmpty(binding.txtAmount.text) || TextUtils.isEmpty(binding.txtRemarks.text)
            ) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                /**
                 * Duplicate Check
                 */
                fleetMovementViewModel.checkDuplicateFleetMovement(
                    url = sBaseURL + FLEET_MOVEMENT_CHECK_DUPLICATE_INFO,
                    sAuthorization = sToken,
                    iGroupID = iGroupID,
                    iSchoolID = iBranchID,
                    iVehicleID = iVehicleID,
                    iOpeningReading = Integer.parseInt(binding.txtOpeningRecord.text.toString()),
                    dMovementDate = binding.lblDateOfRecord.text.toString()
                )
                fleetMovementViewModel.checkDuplicateFleetMovement.observe(viewLifecycleOwner) { duplicate ->
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
        fleetMovementViewModel.retrieveFleetMovementDetails(
            url = sBaseURL + FLEET_MOVEMENT_RETRIEVE_DETAILS,
            sAuthorization = sToken,
            id = iEditID
        )
        fleetMovementViewModel.retrieveFleetMovementDetailsMutableLiveData.observe(
            viewLifecycleOwner
        ) {
            for (items in it) {
                binding.lblDateOfRecord.text = items.sDateOfRecord.substring(0, 10)
                binding.txtOpeningRecord.setText(items.iOpeningReading.toString())
                binding.txtClosingReading.setText(items.iClosingReading.toString())
                binding.txtTripReading.setText(items.iTripReading.toString())
                binding.txtFleetNumber.setText(items.sFleetNumber)
                binding.txtFuelCoupon.setText(items.sFleetCouponNumber)
                binding.txtFuelCoupon.setText(items.dFuelLiters.toString())
                binding.txtAmount.setText(items.dAmount.toString())
                binding.txtRemarks.setText(items.sRemarks)
            }
        }
    }

    /**
     * Post Info
     */
    private fun postInfo() {
        val postInfo = FleetMovementPostInfoItem(
            iGroupId = iGroupID,
            iBranchID = iBranchID,
            iVehicleID = iVehicleID,
            sDateOfRecord = binding.lblDateOfRecord.text.toString(),
            iOpeningReading = Integer.parseInt(binding.txtOpeningRecord.text.toString()),
            iClosingReading = Integer.parseInt(binding.txtClosingReading.text.toString()),
            iTripReading = Integer.parseInt(binding.txtTripReading.text.toString()),
            sFleetNumber = binding.txtFleetNumber.text.toString(),
            sFuelCouponNumber = binding.txtFuelCoupon.text.toString(),
            dFuelLiters = binding.txtFuelLiters.text.toString().toDouble(),
            dAmount = binding.txtAmount.text.toString().toDouble(),
            sRemarks = binding.txtRemarks.text.toString(),
            iUserID = 1,
            iWorkflowStatusID = 1,
            sCreateDate = DateUtils.todayDateOnly().toString(),
            sUpdateDate = DateUtils.todayDateOnly().toString()

        )
        val postParams = FleetMovementPostParams(
            url = sBaseURL + FLEET_MOVEMENT_POST_INFO,
            sAuthorization = sToken,
            fleetMovementPostInfoItem = postInfo
        )
        fleetMovementViewModel.postFleetMovement(postParams)
        fleetMovementViewModel.postFleetMovementMutableLiveData.observe(viewLifecycleOwner) {
            if (it > 0) {
                currentNavController.navigate(R.id.registeredFleetMovementListFragment)
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
        val amendInfo = FleetMovementPostInfoItem(
            iGroupId = iGroupID,
            iBranchID = iBranchID,
            iVehicleID = iVehicleID,
            sDateOfRecord = binding.lblDateOfRecord.text.toString(),
            iOpeningReading = Integer.parseInt(binding.txtOpeningRecord.text.toString()),
            iClosingReading = Integer.parseInt(binding.txtClosingReading.text.toString()),
            iTripReading = Integer.parseInt(binding.txtTripReading.text.toString()),
            sFleetNumber = binding.txtFleetNumber.text.toString(),
            sFuelCouponNumber = binding.txtFuelCoupon.text.toString(),
            dFuelLiters = binding.txtFuelLiters.text.toString().toDouble(),
            dAmount = binding.txtAmount.text.toString().toDouble(),
            sRemarks = binding.txtRemarks.text.toString(),
            iUserID = 1,
            iWorkflowStatusID = 1,
            sCreateDate = DateUtils.todayDateOnly().toString(),
            sUpdateDate = DateUtils.todayDateOnly().toString()

        )
        val amendParams = FleetMovementPostParams(
            url = sBaseURL + FLEET_MOVEMENT_AMEND_INFO,
            sAuthorization = sToken,
            fleetMovementPostInfoItem = amendInfo
        )
        fleetMovementViewModel.amendFleetMovement(amendParams)
        fleetMovementViewModel.amendFleetMovementMutableLiveData.observe(viewLifecycleOwner) {
            if (it > 0) {
                currentNavController.navigate(R.id.registeredFleetMovementListFragment)
            } else {
                showError(
                    getString(R.string.could_not_save_info),
                    getString(R.string.could_not_save_info_desc)
                )
            }
        }
    }


}