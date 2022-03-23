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
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_FUEL_LOG_CHECK_RETRIEVE_LIST
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentFleetFuelLogListBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class FleetFuelLogListFragment : BaseFragment() {
    private lateinit var binding: FragmentFleetFuelLogListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var fuelLogsViewModel: FleetFuelLogsViewModel
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel

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

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]

        fuelLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[FleetFuelLogsViewModel::class.java]

        binding.fabCreateNew.setOnClickListener {
            val bundle = bundleOf("id" to -1)
            currentNavController.navigate(R.id.fleetFuelLogInfoFragment, bundle)
        }

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setUpObservers()
    }

    private fun setUpObservers() {
        tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
            fuelLogsViewModel.retrieveFuelLogsList(
                it.sBaseURL + FLEET_FUEL_LOG_CHECK_RETRIEVE_LIST,
                it.sToken, it.iGroupID, it.iBranchID,
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