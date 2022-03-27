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
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_FUEL_LOG_AMEND_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_FUEL_LOG_CHECK_DUPLICATE_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_FUEL_LOG_CHECK_RETRIEVE_DETAILS
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_FUEL_LOG_POST_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_REGISTRATION_POPULATE_LIST
import com.solutionsmax.gurukoolmax_v3.core.common.PortalIdConstants
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Event
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.ErrorLogsViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentFleetFuelLogInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.PopulateRegisteredFleetList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log.FuelLogsPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fuel_logs.FuelLogsPostParams
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register.RegisteredFleetViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class FleetFuelLogInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentFleetFuelLogInfoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var fleetRegisterViewModel: RegisteredFleetViewModel
    private lateinit var fuelLogsViewModel: FleetFuelLogsViewModel
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel

    private var iVehicleID: Int = -1
    private var iFuelTypeID: Int = -1
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

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        fuelLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[FleetFuelLogsViewModel::class.java]
        fleetRegisterViewModel =
            ViewModelProvider(this, viewModelFactory)[RegisteredFleetViewModel::class.java]
        mastersViewModel = ViewModelProvider(this, viewModelFactory)[MastersViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setupObservers()

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
            }
        }
    }

    private fun setupObservers() {
        tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
            iGroupID = it.iGroupID
            iBranchID = it.iBranchID
            sToken = it.sToken
            sBaseURL = it.sBaseURL

            if (iEditID > 0) {
                binding.btnSubmit.text = getString(R.string.edit)
                retrieveDetails(iEditID)
            }

            populateRegisteredFleet(sBaseURL, sToken)
            populateFuelType(sBaseURL, sToken)

        }
        with(fleetRegisterViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
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

        with(fuelLogsViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
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
                sErrorSource = FleetFuelLogInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

    /**
     * Populate Fuel Type
     */
    private fun populateFuelType(sBaseURL: String, sToken: String) {
        mastersViewModel.populateFuelType(
            url = sBaseURL + MethodConstants.POPULATE_MASTER_LIST,
            sAuthorization = sToken,
            sTableName = MasterTableNames.MASTERS_FLEET_FUEL_TYPES
        )
        mastersViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(it.peekContent())
        }
        mastersViewModel.populateFuelTypeMutableData.observe(viewLifecycleOwner) {
            binding.cboFuelType.apply {
                it.add(
                    0,
                    PopulateMasterListItem(
                        -1,
                        getString(R.string.choose_an_option), "", -1, -1, -1, -1
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
                            val fuelType =
                                binding.cboFuelType.selectedItem as PopulateMasterListItem
                            iFuelTypeID = fuelType.id
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }
            }
        }
    }

    private fun populateRegisteredFleet(sBaseURL: String, sToken: String) {
        fleetRegisterViewModel.populateRegisteredFleetList(
            sBaseURL + FLEET_REGISTRATION_POPULATE_LIST,
            sToken, 1
        )
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
            iFuelTypeID = iFuelTypeID,
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
            iFuelTypeID = iFuelTypeID,
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
    }

}