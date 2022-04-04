package com.solutionsmax.gurukoolmax_v3.operations.ui.information

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_FLEET_BUS_ROUTES
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.TokenViewModel
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentFleetRoutesListBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.adapter.FleetRoutesListAdapter
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.LicenseViewModel
import javax.inject.Inject


class FleetRoutesListFragment : BaseFragment() {

    private lateinit var binding: FragmentFleetRoutesListBinding

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
        binding = FragmentFleetRoutesListBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = getString(R.string.bus_routes)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                val bundle = bundleOf("menu" to OperationMenuConstants.FLEET_INFORMATION)
                setNavigationOnClickListener {
                    currentNavController.navigate(
                        R.id.operationsSubMenuFragment,
                        bundle
                    )
                }
            }
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
            fleetViewModel.retrieveFleetBusRoutes(
                sBaseURL + RETRIEVE_FLEET_BUS_ROUTES, sToken, -1
            )
            fleetViewModel.fleetRoutesMutableData.observe(viewLifecycleOwner) {
                binding.progressBar.visibility = View.GONE
                with(binding.fleetRouteList) {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = FleetRoutesListAdapter(it)
                }
            }
        }
    }

}