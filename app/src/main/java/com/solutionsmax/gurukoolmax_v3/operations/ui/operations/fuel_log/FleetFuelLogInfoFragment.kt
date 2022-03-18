package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.fuel_log

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
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_FUEL_LOG_AMEND_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_FUEL_LOG_CHECK_DUPLICATE_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_FUEL_LOG_CHECK_RETRIEVE_DETAILS
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_FUEL_LOG_POST_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_REGISTRATION_POPULATE_LIST
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.TokenViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentFleetFuelLogInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.PopulateRegisteredFleetList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log.FuelLogsPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fuel_logs.FuelLogsPostParams
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register.RegisteredFleetViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.LicenseViewModel
import javax.inject.Inject


class FleetFuelLogInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentFleetFuelLogInfoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var licenseViewModel: LicenseViewModel
    private lateinit var fleetRegisterViewModel: RegisteredFleetViewModel
    private lateinit var fuelLogsViewModel: FleetFuelLogsViewModel

    private var iVehicleID: Int = -1
    private var iEditID: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFleetFuelLogInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            title = getString(R.string.fleet_fuel_log)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { currentNavController.navigate(R.id.fleetFuelLogListFragment) }
        }

        iEditID = requireArguments().getInt("id", -1)
        binding.lblPurchaseDate.text =
            DateUtils.todayDateTime().getMediumDateFormat(requireContext())

        tokenViewModel = ViewModelProvider(this, viewModelFactory)[TokenViewModel::class.java]
        licenseViewModel = ViewModelProvider(this, viewModelFactory)[LicenseViewModel::class.java]
        fuelLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[FleetFuelLogsViewModel::class.java]
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
                with(fleetRegisterViewModel) {
                    errorLiveData.observe(viewLifecycleOwner) {
                        showError(it.peekContent())
                    }
                    populateRegisteredFleetMutableData.observe(viewLifecycleOwner) {
                        binding.cboVehicleName.apply {
                            it.add(
                                0,
                                PopulateRegisteredFleetList(
                                    -1,
                                    getString(R.string.choose_an_option)
                                )
                            )
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
        }

        binding.btnSubmit.setOnClickListener {
            if (iVehicleID == -1 || TextUtils.isEmpty(binding.txtOdometer.text) ||
                TextUtils.isEmpty(binding.txtGallons.text) || TextUtils.isEmpty(binding.txtTotalCost.text) ||
                TextUtils.isEmpty(binding.txtProviderName.text)
            ) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                /**
                 * Duplicate Check
                 */
                fuelLogsViewModel.checkDuplicateFuelLogs(
                    sBaseURL + FLEET_FUEL_LOG_CHECK_DUPLICATE_INFO, sAuthorization = sToken,
                    iGroupID = iGroupID, iSchoolID = iBranchID, iVehicleID, -1
                )
                with(fuelLogsViewModel) {
                    errorLiveData.observe(viewLifecycleOwner) {
                        showError(it.peekContent())
                    }
                    checkDuplicateFuelLogsMutableData.observe(viewLifecycleOwner) { duplicate ->
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
    }

    /**
     * Retrieve Details
     */
    private fun retrieveDetails(iEditID: Int) {
        fuelLogsViewModel.retrieveFuelLogsDetails(
            url = sBaseURL + FLEET_FUEL_LOG_CHECK_RETRIEVE_DETAILS,
            sAuthorization = sToken,
            id = iEditID
        )
        with(fuelLogsViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
            }
            retrieveFuelLogsDetailsMutableData.observe(viewLifecycleOwner) {
                for (items in it) {
                    binding.lblPurchaseDate.text = items.sDateOfPurchase.substring(0, 10)
                    binding.txtOdometer.setText(items.iOdometer.toString())
                    binding.txtGallons.setText(items.iGallons.toString())
                    binding.txtTotalCost.setText(items.iTotalCost.toString())
                    binding.txtProviderName.setText(items.sProviderName)
                    binding.txtRemarks.setText(items.sNotes)
                }
            }
        }
    }

    /**
     * Post Info
     */
    private fun postInfo() {
        val postItems = FuelLogsPostInfoItem(
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            iFleetID = iVehicleID,
            sDateOfPurchase = binding.lblPurchaseDate.text.toString(),
            iOdometer = Integer.parseInt(binding.txtOdometer.text.toString()),
            dGallons = binding.txtGallons.text.toString().toDouble(),
            dTotalCost = binding.txtTotalCost.text.toString().toDouble(),
            iFuelTypeID = -1,
            sProviderName = binding.txtProviderName.text.toString(),
            sNotes = binding.txtRemarks.text.toString(),
            iUserID = 1,
            iWorkflowStatusID = 1,
            sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
            sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
        )
        val postParams = FuelLogsPostParams(
            url = sBaseURL + FLEET_FUEL_LOG_POST_INFO,
            sAuthorization = sToken,
            fuelLogsPostInfoItem = postItems
        )
        fuelLogsViewModel.postFuelLogs(postParams)
        with(fuelLogsViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
            }
            postFuelLogsMutableData.observe(viewLifecycleOwner) {
                if (it > 0) {
                    currentNavController.navigate(R.id.fleetFuelLogListFragment)
                } else {
                    showError(
                        getString(R.string.could_not_save_info),
                        getString(R.string.could_not_save_info_desc)
                    )
                }
            }
        }
    }

    /**
     * Amend Info
     */
    private fun amendInfo() {
        val amendItems = FuelLogsPostInfoItem(
            id = iEditID,
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            iFleetID = iVehicleID,
            sDateOfPurchase = binding.lblPurchaseDate.text.toString(),
            iOdometer = Integer.parseInt(binding.txtOdometer.text.toString()),
            dGallons = binding.txtGallons.text.toString().toDouble(),
            dTotalCost = binding.txtTotalCost.text.toString().toDouble(),
            iFuelTypeID = -1,
            sProviderName = binding.txtProviderName.text.toString(),
            sNotes = binding.txtRemarks.text.toString(),
            iUserID = 1,
            iWorkflowStatusID = 1,
            sCreateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext()),
            sUpdateDate = DateUtils.todayDateTime().getMediumDateFormat(requireContext())
        )
        val amendParams = FuelLogsPostParams(
            url = sBaseURL + FLEET_FUEL_LOG_AMEND_INFO,
            sAuthorization = sToken,
            fuelLogsPostInfoItem = amendItems
        )
        fuelLogsViewModel.amendFuelLogs(amendParams)
        with(fuelLogsViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
            }
            amendFuelLogsMutableData.observe(viewLifecycleOwner) {
                if (it > 0) {
                    currentNavController.navigate(R.id.fleetFuelLogListFragment)
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