package com.solutionsmax.gurukoolmax_v3.operations.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentOperationsSubMenuBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.FLEET_INFORMATION
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.FLEET_OPERATIONS
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationsMenuItem
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationsSubMenuConstants.BUS_STOPS
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationsSubMenuConstants.FLEET_FUEL_LOG
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationsSubMenuConstants.FLEET_MOVEMENT
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationsSubMenuConstants.FLEET_REGISTER
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationsSubMenuConstants.PICKUP_TIMING
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationsSubMenuConstants.ROUTE_MASTERS
import com.solutionsmax.gurukoolmax_v3.operations.ui.adapter.OperationsMenuAdapter


class OperationsSubMenuFragment : BaseFragment() {

    private lateinit var binding: FragmentOperationsSubMenuBinding
    private lateinit var menuType: String
    private var menuItems = emptyList<OperationsMenuItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOperationsSubMenuBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuType = requireArguments().getString("menu").toString()

        binding.toolbar.apply {
            title = getString(R.string.fleet_options)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { currentNavController.navigate(R.id.operationsMenuFragment) }
        }

        when (menuType) {
            FLEET_INFORMATION -> {
                menuItems = listOf(
                    OperationsMenuItem(ROUTE_MASTERS, R.drawable.fleet_info),
                    OperationsMenuItem(BUS_STOPS, R.drawable.fleet_info),
                    OperationsMenuItem(PICKUP_TIMING, R.drawable.fleet_info),
                )
            }
            FLEET_OPERATIONS -> {
                menuItems = listOf(
                    OperationsMenuItem(FLEET_REGISTER, R.drawable.fleet_info),
                    OperationsMenuItem(FLEET_MOVEMENT, R.drawable.fleet_info),
                    OperationsMenuItem(FLEET_FUEL_LOG, R.drawable.fleet_info),
                )

            }
        }

        with(binding.operationsSubMenu) {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = OperationsMenuAdapter(menuItems, OperationsMenuAdapter.OnItemClick {
                when (it) {
                    ROUTE_MASTERS -> currentNavController.navigate(R.id.fleetRoutesListFragment)
                    BUS_STOPS -> currentNavController.navigate(R.id.fleetBusStopsListFragment)
                    PICKUP_TIMING -> currentNavController.navigate(R.id.fleetSchedulePickupListFragment)

                    FLEET_REGISTER -> currentNavController.navigate(R.id.registeredFleetListFragment)
                    FLEET_MOVEMENT -> currentNavController.navigate(R.id.registeredFleetMovementListFragment)
                    FLEET_FUEL_LOG -> currentNavController.navigate(R.id.fleetFuelLogListFragment)
                }
            })
        }
    }
}