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
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_FLEET_BUS_STOPS
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.TokenViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.LocationAddress
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentFleetBusStopsListBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants
import com.solutionsmax.gurukoolmax_v3.operations.ui.gps_tracker.SendLocation
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.adapter.FleetBusStopListAdapter
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.LicenseViewModel
import javax.inject.Inject


class FleetBusStopsListFragment : BaseFragment() {

    private lateinit var binding: FragmentFleetBusStopsListBinding
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var address: String = ""
    private var sAuthToken: String = ""

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var licenseViewModel: LicenseViewModel
    private lateinit var fleetViewModel: FleetRoutesViewModel



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

        binding.progressBar.visibility = View.VISIBLE
        tokenViewModel = ViewModelProvider(this, viewModelFactory)[TokenViewModel::class.java]
        licenseViewModel = ViewModelProvider(this, viewModelFactory)[LicenseViewModel::class.java]
        fleetViewModel = ViewModelProvider(this, viewModelFactory)[FleetRoutesViewModel::class.java]

        tokenViewModel.retrieveTokensFromLocal()
        tokenViewModel.retrieveTokenLiveData.observe(viewLifecycleOwner) {
            sToken = it.first().access_token
            sAuthToken = it.first().access_token
        }

        licenseViewModel.retrieveLicenseInfo()
        licenseViewModel.retrieveLicenseInfoUseCase.observe(viewLifecycleOwner) {
            sBaseURL = it.first().rest_url
            fleetViewModel.retrieveFleetBusStop(
                sBaseURL + RETRIEVE_FLEET_BUS_STOPS, sToken, -1
            )
            fleetViewModel.fleetBusStopMutableData.observe(viewLifecycleOwner) {
                binding.progressBar.visibility = View.GONE
                with(binding.fleetBusStops) {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = FleetBusStopListAdapter(it, FleetBusStopListAdapter.OnItemClick {
                        context.startService(Intent(context, SendLocation::class.java))
                        LocalBroadcastManager.getInstance(context)
                            .registerReceiver(mMessageReceiver, IntentFilter("GPSLocationUpdates"))
                    })
                }
            }
        }
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
                Log.d("TAG", "onReceive: " + lastKnownLoc.latitude)
                val locationAddress = LocationAddress()
                locationAddress.getAddressFromLocation(
                    lastKnownLoc.latitude,
                    lastKnownLoc.longitude,
                    context,
                    GeocoderHandler()
                )
            }
        }
    }

    class GeocoderHandler : Handler() {

    }

    /*private class GeocoderHandler : Handler() {
        override fun handleMessage(message: Message) {
            val locationAddress: String? = when (message.what) {
                1 -> {
                    val bundle = message.data
                    bundle.getString("address")
                }
                else -> null
            }
            address = locationAddress
            Log.d("TAG", "onReceiveAddress: $locationAddress")
            checkDuplicateAddress(sAuthToken, iRouteID)
        }
    }*/
}