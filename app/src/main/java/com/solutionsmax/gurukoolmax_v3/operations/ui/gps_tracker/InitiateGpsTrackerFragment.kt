package com.solutionsmax.gurukoolmax_v3.operations.ui.gps_tracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.PortalIdConstants
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Event
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.ErrorLogsViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.core.utils.LocationAddress
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentInitiateGpsTrackerBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_gps.FleetGPSPostItem
import com.solutionsmax.gurukoolmax_v3.operations.ui.gps_tracker.adapter.InitiateGpsTrackerAdapter
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetGPSViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetRoutesViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class InitiateGpsTrackerFragment : BaseFragment() {

    private lateinit var binding: FragmentInitiateGpsTrackerBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var fleetViewModel: FleetRoutesViewModel
    lateinit var fleetGPSViewModel: FleetGPSViewModel
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel

    private var iRouteID: Int = -1

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var address: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInitiateGpsTrackerBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = getString(R.string.gps_tracker)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.operationsMenuFragment
                )
            }
        }

        binding.progressBar.visibility = View.VISIBLE

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        fleetViewModel = ViewModelProvider(this, viewModelFactory)[FleetRoutesViewModel::class.java]
        fleetGPSViewModel = ViewModelProvider(this, viewModelFactory)[FleetGPSViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setUpObservers()

    }

    private fun setUpObservers() {
        tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
            sBaseURL = it.sBaseURL
            sToken = it.sToken
            sAssetURL = it.sAssetURL
            iGroupID = it.iGroupID
            iBranchID = it.iBranchID
            fleetViewModel.retrieveFleetBusStop(
                it.sBaseURL + MethodConstants.RETRIEVE_FLEET_BUS_STOPS, it.sToken, -1
            )
            with(fleetViewModel) {
                errorLiveData.observe(viewLifecycleOwner) { error ->
                    showError(error.peekContent())
                    with(errorLogsViewModel) {
                        postErrors(error)
                        postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                    }
                }
                fleetBusStopMutableData.observe(viewLifecycleOwner) {
                    binding.progressBar.visibility = View.GONE
                    with(binding.fleetBusStops) {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter =
                            InitiateGpsTrackerAdapter(
                                it,
                                InitiateGpsTrackerAdapter.OnItemClick { item ->
                                    iRouteID = item
                                    context.startService(
                                        Intent(
                                            context,
                                            SendLocation::class.java
                                        )
                                    )
                                    LocalBroadcastManager.getInstance(context)
                                        .registerReceiver(
                                            mMessageReceiver,
                                            IntentFilter("GPSLocationUpdates")
                                        )
                                })
                    }
                }
            }

            with(fleetGPSViewModel) {
                errorLiveData.observe(viewLifecycleOwner) { error ->
                    showError(error.peekContent())
                    with(errorLogsViewModel) {
                        postErrors(error)
                        postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                    }
                }
                checkDuplicateFleetGPSMutableData.observe(viewLifecycleOwner) { duplicate ->
                    if (duplicate > 0) {
                        amendInfo()
                    } else {
                        postInfo()
                    }
                }

                postFleetGPSMutableData.observe(viewLifecycleOwner) {}

                amendFleetGPSMutableData.observe(viewLifecycleOwner){}
            }
        }
    }

    private fun postInfo() {
        fleetGPSViewModel.postFleetGPS(
            url = sBaseURL,
            sAuthorization = sToken,
            fleetGPSPostItem = FleetGPSPostItem(
                iGroupID = iGroupID,
                iSchoolID = iBranchID,
                iRouteID = iRouteID,
                iDriverID = -1,
                sMobileNumber = "",
                sIpAddress = "",
                sAddress = address,
                sCity = "",
                sState = "",
                sCountry = "",
                sPostalCode = "",
                sKnownName = address,
                sLatitude = latitude.toString(),
                sLongitude = longitude.toString(),
                sSyncDateTime = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                iWorkflowStatusID = 1,
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

    private fun amendInfo() {
        fleetGPSViewModel.amendFleetGPS(
            url = sBaseURL,
            sAuthorization = sToken,
            fleetGPSPostItem = FleetGPSPostItem(
                id = iRouteID,
                iGroupID = iGroupID,
                iSchoolID = iBranchID,
                iRouteID = iRouteID,
                iDriverID = -1,
                sMobileNumber = "",
                sIpAddress = "",
                sAddress = address,
                sCity = "",
                sState = "",
                sCountry = "",
                sPostalCode = "",
                sKnownName = address,
                sLatitude = latitude.toString(),
                sLongitude = longitude.toString(),
                sSyncDateTime = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                iWorkflowStatusID = 1,
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Get extra data included in the Intent
            val message = intent.getStringExtra("Status")
            val b = intent.getBundleExtra("Location")
            val lastKnownLoc = b!!.getParcelable<Parcelable>("Location") as Location?
            if (lastKnownLoc != null) {
                latitude = lastKnownLoc.latitude
                longitude = lastKnownLoc.longitude
                LocationAddress().getAddressFromLocation(
                    lastKnownLoc.latitude,
                    lastKnownLoc.longitude,
                    context,
                    GeocoderHandler()
                )
            }
        }
    }

    internal inner class GeocoderHandler : Handler() {
        override fun handleMessage(message: Message) {
            val locationAddress: String? = when (message.what) {
                1 -> {
                    val bundle = message.data
                    bundle.getString("address")
                }
                else -> null
            }
            address = locationAddress!!

            checkDuplicateAddress(address)
        }
    }

    private fun checkDuplicateAddress(sAddress: String) {
        fleetGPSViewModel.checkDuplicateFleetGPS(
            url = sBaseURL,
            sAuthorization = sToken,
            sAddress = sAddress,
            sLongitude = longitude.toString(),
            sLatitude = latitude.toString(),
            sSyncDT = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext()),
            iRouteID = iRouteID,
            iDriverID = -1
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
                sErrorSource = InitiateGpsTrackerFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

}