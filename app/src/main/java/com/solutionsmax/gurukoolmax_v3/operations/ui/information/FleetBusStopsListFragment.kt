package com.solutionsmax.gurukoolmax_v3.operations.ui.information

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
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
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentFleetBusStopsListBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants
import com.solutionsmax.gurukoolmax_v3.operations.ui.gps_tracker.SendLocation
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.adapter.FleetBusStopListAdapter
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class FleetBusStopsListFragment : BaseFragment() {

    private lateinit var binding: FragmentFleetBusStopsListBinding
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var address: String = ""
    private var sAuthToken: String = ""

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var fleetViewModel: FleetRoutesViewModel
    lateinit var fleetGPSViewModel: FleetGPSViewModel
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel

    private var iRouteID: Int = -1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFleetBusStopsListBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = getString(R.string.bus_stops)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            val bundle = bundleOf("menu" to OperationMenuConstants.FLEET_INFORMATION)
            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.operationsSubMenuFragment,
                    bundle
                )
            }
        }

        binding.fabCreateNew.setOnClickListener {
            val bundle = bundleOf("id" to -1)
            currentNavController.navigate(
                R.id.fleetBusStopInfoFragment,
                bundle
            )
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
            sAuthToken = it.sToken
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
            }
            with(fleetViewModel) {
                fleetBusStopMutableData.observe(viewLifecycleOwner) {
                    binding.progressBar.visibility = View.GONE
                    with(binding.fleetBusStops) {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter =
                            FleetBusStopListAdapter(
                                it,
                                FleetBusStopListAdapter.OnItemClick { item ->
                                    val bundle = bundleOf("id" to item)
                                    currentNavController.navigate(
                                        R.id.fleetBusStopInfoFragment,
                                        bundle
                                    )
                                }
                            )
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
                sErrorSource = FleetBusStopsListFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
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
                    GeocoderHandler(address, sAuthToken, iRouteID)
                ).let {
                    Handler(Looper.getMainLooper()).postDelayed({
                        Log.d("TAG", "onReceive: Address $address")
                    }, 3000)
                }
            }
        }
    }

    private class GeocoderHandler(address: String, sAuthToken: String, iRouteID: Int) : Handler() {
        var sTestAddress = address
        var sTestToken = sAuthToken
        var iTestRouteID = iRouteID
        override fun handleMessage(message: Message) {
            val locationAddress: String? = when (message.what) {
                1 -> {
                    val bundle = message.data
                    bundle.getString("address")
                }
                else -> null
            }
            sTestAddress = locationAddress!!

            checkDuplicateAddress(sTestToken, iTestRouteID)
        }

        private fun checkDuplicateAddress(sTestToken: String, iTestRouteID: Int) {

        }
    }
}