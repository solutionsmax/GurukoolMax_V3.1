package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.movement

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
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_MOVEMENT_AMEND_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_MOVEMENT_CHECK_DUPLICATE_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_MOVEMENT_POST_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_MOVEMENT_RETRIEVE_DETAILS
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_REGISTRATION_POPULATE_LIST
import com.solutionsmax.gurukoolmax_v3.core.common.PortalIdConstants
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Event
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.ErrorLogsViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentRegisteredFleetMovementInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.PopulateRegisteredFleetList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_movement.FleetMovementPostParams
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.movement.spinner_adapter.FMVehicleSpinnerAdapter
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register.RegisteredFleetViewModel
import javax.inject.Inject

class RegisteredFleetMovementInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisteredFleetMovementInfoBinding

    companion object {
        var lblVehicleName: TextView? = null
        var iVehicleID: Int = -1
        var dialog: Dialog? = null
    }

    private lateinit var populateRegisteredFleetList: List<PopulateRegisteredFleetList>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var fleetRegisterViewModel: RegisteredFleetViewModel
    private lateinit var fleetMovementViewModel: FleetMovementViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
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

        lblVehicleName = view.findViewById(R.id.cboVehicleName)

        iEditID = requireArguments().getInt("id", -1)
        binding.lblDateOfRecord.text =
            DateUtils.todayDateTime().getMediumDateFormat(requireContext())

        fleetMovementViewModel =
            ViewModelProvider(this, viewModelFactory)[FleetMovementViewModel::class.java]
        fleetRegisterViewModel =
            ViewModelProvider(this, viewModelFactory)[RegisteredFleetViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]

        fleetRegisterViewModel.retrieveTokenLicenseInfo()

        setupViewModelObservers()

        lblVehicleName!!.setOnClickListener { showVehicleNameDialog() }

        binding.btnSubmit.setOnClickListener {

            if (Integer.parseInt(binding.txtClosingReading.text.toString()) < Integer.parseInt(
                    binding.txtOpeningRecord.text.toString()
                )
            ) {
                showError(
                    getString(R.string.range_is_not_valid),
                    getString(R.string.range_is_not_valid_desc)
                )
            } else {
                if (TextUtils.isEmpty(binding.txtOpeningRecord.text) ||
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
                    binding.progressBar.visibility = View.VISIBLE
                    fleetMovementViewModel.checkDuplicateFleetMovement(
                        url = sBaseURL + FLEET_MOVEMENT_CHECK_DUPLICATE_INFO,
                        sAuthorization = sToken,
                        iGroupID = iGroupID,
                        iSchoolID = iBranchID,
                        iVehicleID = iVehicleID,
                        iOpeningReading = Integer.parseInt(binding.txtOpeningRecord.text.toString()),
                        dMovementDate = binding.lblDateOfRecord.text.toString()
                    )
                }
            }

        }

    }

    private fun setupViewModelObservers() {
        with(fleetRegisterViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(error = it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
            }
            tokenLicenseMutableData.observe(viewLifecycleOwner) {
                sBaseURL = it.sBaseURL
                sToken = it.sToken
                sAssetURL = it.sAssetURL
                iGroupID = it.iGroupID
                iBranchID = it.iBranchID

                populateVehicleName(sBaseURL, sToken)

                if (iEditID > 0) {
                    binding.btnSubmit.text = getString(R.string.edit)
                    retrieveDetails(iEditID)
                }
            }
        }
        with(fleetMovementViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
            }
            checkDuplicateFleetMovement.observe(viewLifecycleOwner) { duplicate ->
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

            postFleetMovementMutableLiveData.observe(viewLifecycleOwner) {
                if (it > 0) {
                    currentNavController.navigate(R.id.registeredFleetMovementListFragment)
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                    showError(
                        getString(R.string.could_not_save_info),
                        getString(R.string.could_not_save_info_desc)
                    )
                }
            }

            amendFleetMovementMutableLiveData.observe(viewLifecycleOwner) {
                if (it > 0) {
                    currentNavController.navigate(R.id.registeredFleetMovementListFragment)
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
                sErrorSource = RegisteredFleetMovementInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

    /**
     * Populate Vehicle Name
     */
    private fun populateVehicleName(sBaseURL: String, sToken: String) {
        fleetRegisterViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(error = it.peekContent())
            with(errorLogsViewModel) {
                postErrors(it)
                postErrorLogsMutableData.observe(viewLifecycleOwner) {}
            }
        }
        fleetRegisterViewModel.populateRegisteredFleetList(
            sBaseURL + FLEET_REGISTRATION_POPULATE_LIST,
            sToken, -1
        )
        fleetRegisterViewModel.populateRegisteredFleetMutableData.observe(viewLifecycleOwner) {
            populateRegisteredFleetList = it
        }
    }

    private fun showVehicleNameDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.vehicle_name)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FMVehicleSpinnerAdapter(
                populateRegisteredFleetList,
                FMVehicleSpinnerAdapter.OnItemClick {
                    lblVehicleName!!.text = it.sVehicleName
                    iVehicleID = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    /**
     * Retrieve Details
     */
    private fun retrieveDetails(iEditID: Int) {
        fleetRegisterViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(error = it.peekContent())
            with(errorLogsViewModel) {
                postErrors(it)
                postErrorLogsMutableData.observe(viewLifecycleOwner) {}
            }
        }
        fleetMovementViewModel.retrieveFleetMovementDetails(
            url = sBaseURL + FLEET_MOVEMENT_RETRIEVE_DETAILS,
            sAuthorization = sToken,
            id = iEditID
        )
        with(fleetMovementViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
            }
            retrieveFleetMovementDetailsMutableLiveData.observe(
                viewLifecycleOwner
            ) {
                binding.lblClosingReading.visibility = View.VISIBLE
                binding.layoutClosingReading.visibility = View.VISIBLE
                for (items in it) {
                    binding.lblDateOfRecord.text = items.sDateOfRecord.substring(0, 10)
                    binding.txtOpeningRecord.setText(items.iOpeningReading.toString())
                    if (items.iClosingReading == -1) {
                        binding.txtClosingReading.setText("")
                    } else {
                        binding.txtClosingReading.setText(items.iClosingReading.toString())
                    }

                    binding.txtTripReading.setText(items.iTripReading.toString())
                    binding.txtFleetNumber.setText(items.sFleetNumber)
                    binding.txtFuelCoupon.setText(items.sFleetCouponNumber)
                    binding.txtFuelLiters.setText(items.dFuelLiters.toString())
                    binding.txtAmount.setText(items.dAmount.toString())
                    binding.txtRemarks.setText(items.sRemarks)
                    binding.cboVehicleName.text = items.sVehicleName
                    iVehicleID = items.iVehicleID
                }
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
            iClosingReading = -1,
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
    }

    /**
     * Amend Info
     */
    private fun amendInfo() {
        val amendInfo = FleetMovementPostInfoItem(
            id = iEditID,
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
    }


}