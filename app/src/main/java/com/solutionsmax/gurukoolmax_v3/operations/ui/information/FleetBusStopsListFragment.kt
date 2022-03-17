package com.solutionsmax.gurukoolmax_v3.operations.ui.information

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_FLEET_BUS_STOPS
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.TokenViewModel
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentFleetBusStopsListBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.adapter.FleetBusStopListAdapter
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.LicenseViewModel
import javax.inject.Inject


class FleetBusStopsListFragment : BaseFragment() {

    private lateinit var binding: FragmentFleetBusStopsListBinding

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
            setNavigationOnClickListener { currentNavController.navigate(R.id.operationsSubMenuFragment) }
        }

        binding.progressBar.visibility = View.VISIBLE
        tokenViewModel = ViewModelProvider(this, viewModelFactory)[TokenViewModel::class.java]
        licenseViewModel = ViewModelProvider(this, viewModelFactory)[LicenseViewModel::class.java]
        fleetViewModel = ViewModelProvider(this, viewModelFactory)[FleetRoutesViewModel::class.java]

        tokenViewModel.retrieveTokensFromLocal()
        tokenViewModel.retrieveTokenLiveData.observe(viewLifecycleOwner) {
            sToken = it.first().access_token
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
                    adapter = FleetBusStopListAdapter(it)
                }
            }
        }
    }

}