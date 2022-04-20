package com.solutionsmax.gurukoolmax_v3.operations.ui.gps_tracker

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
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
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentViewGpsMapBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.PopulateBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetGPSViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetRoutesViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class ViewGpsMapFragment : BaseFragment(), OnMapReadyCallback, OnMarkerClickListener {

    private lateinit var binding: FragmentViewGpsMapBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var fleetRoutesViewModel: FleetRoutesViewModel
    private lateinit var fleetGPSViewModel: FleetGPSViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentViewGpsMapBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.map.onCreate(savedInstanceState)
        binding.map.onResume()

        try {
            MapsInitializer.initialize(requireActivity().applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.toolbar.apply {
            title = getString(R.string.view_gps_map)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                val bundle = bundleOf("menu" to OperationMenuConstants.FLEET_GPS_TRACKER)
                currentNavController.navigate(
                    R.id.operationsSubMenuFragment, bundle
                )
            }
        }

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        mastersViewModel = ViewModelProvider(this, viewModelFactory)[MastersViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]
        fleetRoutesViewModel =
            ViewModelProvider(this, viewModelFactory)[FleetRoutesViewModel::class.java]
        fleetGPSViewModel = ViewModelProvider(this, viewModelFactory)[FleetGPSViewModel::class.java]

        binding.map.getMapAsync(this)
    }

    private fun setupObservers(googleMap: GoogleMap) {
        tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
            sBaseURL = it.sBaseURL
            sToken = it.sToken

            populateRoute(sBaseURL, sToken)
        }
        with(fleetRoutesViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(error = it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
            }
            populateBusRoutesMutableData.observe(viewLifecycleOwner) { populateBusRoutes ->
                populateBusRoutes.add(
                    0,
                    PopulateBusRoutesItems(-1, -1, -1, "Choose an Option", -1, "", "")
                )
                binding.cboRoutes.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    populateBusRoutes
                )
                binding.cboRoutes.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            p2: Int,
                            p3: Long
                        ) {
                            if (p2 > 0) {
                                val route: PopulateBusRoutesItems =
                                    binding.cboRoutes.selectedItem as PopulateBusRoutesItems
                                retrieveMapMarkers(sToken, route.id, googleMap)
                            }
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {

                        }

                    }
            }
        }

        with(fleetGPSViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(error = it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
            }
            retrieveFleetGPSListMutableData.observe(viewLifecycleOwner) {
                googleMap.clear()
                for (items in it) {
                    googleMap.addMarker(
                        MarkerOptions().position(
                            LatLng(
                                items.sLatitude.toDouble(),
                                items.sLongitude.toDouble()
                            )
                        ).title(items.sAddress)
                    )
                    googleMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            LatLng(
                                items.sLatitude.toDouble(),
                                items.sLongitude.toDouble()
                            ), 10f
                        )
                    )
                }

            }
        }
    }

    private fun retrieveMapMarkers(sToken: String, iRouteID: Int, googleMap: GoogleMap) {
        fleetGPSViewModel.retrieveFleetGPSList(
            url = sBaseURL,
            sAuthorization = sToken,
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
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
                sErrorSource = ViewGpsMapFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

    private fun populateRoute(sBaseURL: String, sToken: String) {
        fleetRoutesViewModel.populateBusRouteName(
            url = sBaseURL,
            sAuthorization = sToken,
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            iStatusID = -1
        )
    }

    override fun onMapReady(p0: GoogleMap) {
        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setupObservers(p0)
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        Toast.makeText(context, p0.title, Toast.LENGTH_LONG).show()
        return false
    }


    override fun onPause() {
        super.onPause()
        binding.map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.map.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.map.onLowMemory()
    }
}