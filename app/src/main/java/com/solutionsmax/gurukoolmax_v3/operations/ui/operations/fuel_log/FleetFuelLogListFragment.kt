package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.fuel_log

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_FUEL_LOG_CHECK_RETRIEVE_LIST
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.TokenViewModel
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentFleetFuelLogListBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.movement.FleetMovementAdapter
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.LicenseViewModel
import javax.inject.Inject


class FleetFuelLogListFragment : BaseFragment() {
    private lateinit var binding: FragmentFleetFuelLogListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var licenseViewModel: LicenseViewModel
    private lateinit var fuelLogsViewModel: FleetFuelLogsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFleetFuelLogListBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = getString(R.string.fleet_fuel_log)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { currentNavController.navigate(R.id.operationsMenuFragment) }
        }

        tokenViewModel = ViewModelProvider(this, viewModelFactory)[TokenViewModel::class.java]
        licenseViewModel = ViewModelProvider(this, viewModelFactory)[LicenseViewModel::class.java]
        fuelLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[FleetFuelLogsViewModel::class.java]

        binding.fabCreateNew.setOnClickListener {
            val bundle = bundleOf("id" to -1)
            currentNavController.navigate(R.id.fleetFuelLogInfoFragment, bundle)
        }

        tokenViewModel.retrieveTokensFromLocal()
        tokenViewModel.retrieveTokenLiveData.observe(viewLifecycleOwner) { token ->
            licenseViewModel.retrieveLicenseInfo()
            licenseViewModel.retrieveLicenseInfoUseCase.observe(viewLifecycleOwner) { license ->
                fuelLogsViewModel.retrieveFuelLogsList(
                    license.first().rest_url + FLEET_FUEL_LOG_CHECK_RETRIEVE_LIST,
                    token.first().access_token, license.first().group_id, license.first().branch_id,
                    -1, -1
                )
                with(fuelLogsViewModel) {
                    retrieveFuelLogsListMutableData.observe(
                        viewLifecycleOwner
                    ) {
                        errorLiveData.observe(viewLifecycleOwner) {
                            showError(it.peekContent())
                        }
                        binding.progressBar.visibility = View.GONE
                        with(binding.registeredFleetList) {
                            layoutManager = LinearLayoutManager(requireContext())
                            adapter = FleetFuelLogsAdapter(it, FleetFuelLogsAdapter.OnItemClick {
                                val bundle = bundleOf("id" to it)
                                currentNavController.navigate(
                                    R.id.fleetFuelLogInfoFragment,
                                    bundle
                                )
                            })
                        }
                    }
                }
            }
        }
    }

}