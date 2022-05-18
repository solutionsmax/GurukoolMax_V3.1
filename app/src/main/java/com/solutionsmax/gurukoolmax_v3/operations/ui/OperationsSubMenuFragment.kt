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
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.ACADEMIC_PROJECT
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.CCE_SCHOLASTIC
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.CURRICULUM_MANAGEMENT
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.EXAMINATION_MANAGEMENT
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.KNOWLEDGE_MANAGEMENT
import com.solutionsmax.gurukoolmax_v3.operations.data.GpsTrackerConstants.INITIATE_TRACKER
import com.solutionsmax.gurukoolmax_v3.operations.data.GpsTrackerConstants.VIEW_TRACKER_IN_MAPS
import com.solutionsmax.gurukoolmax_v3.operations.data.OnBoardAttendanceConstants.AUTO_ATTENDANCE
import com.solutionsmax.gurukoolmax_v3.operations.data.OnBoardAttendanceConstants.MANUAL_ATTENDANCE
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.ACADEMICS
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.FLEET_GPS_TRACKER
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.FLEET_INFORMATION
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.FLEET_OPERATIONS
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.ON_BOARD_ATTENDANCE
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
    private var menuType: String = ""
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

        menuType = requireArguments().getString("menu", "-1").toString()


        binding.toolbar.apply {
            title = when (menuType) {
                ON_BOARD_ATTENDANCE -> getString(R.string.on_board_attendance)
                FLEET_OPERATIONS -> getString(R.string.fleet_operations)
                FLEET_GPS_TRACKER -> getString(R.string.fleet_gps)
                FLEET_INFORMATION -> getString(R.string.fleet_information)
                ACADEMICS -> getString(R.string.academics)
                else -> getString(R.string.fleet_options)
            }
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { currentNavController.navigate(R.id.operationsMenuFragment) }
        }

        when (menuType) {
            FLEET_INFORMATION -> {
                menuItems = listOf(
                    OperationsMenuItem(ROUTE_MASTERS, R.drawable.route_masters),
                    OperationsMenuItem(BUS_STOPS, R.drawable.bus_stop),
                    OperationsMenuItem(PICKUP_TIMING, R.drawable.pickup_timing),
                )
            }
            FLEET_OPERATIONS -> {
                menuItems = listOf(
                    OperationsMenuItem(FLEET_REGISTER, R.drawable.register_fleet),
                    OperationsMenuItem(FLEET_MOVEMENT, R.drawable.fleet_movement),
                    OperationsMenuItem(FLEET_FUEL_LOG, R.drawable.fleet_fuel_log),
                )
            }
            ON_BOARD_ATTENDANCE -> {
                menuItems = listOf(
                    OperationsMenuItem(MANUAL_ATTENDANCE, R.drawable.take_attendance_by_admission),
                    OperationsMenuItem(AUTO_ATTENDANCE, R.drawable.scan_admission_number),
                )
            }
            FLEET_GPS_TRACKER -> {
                menuItems = listOf(
                    OperationsMenuItem(INITIATE_TRACKER, R.drawable.initiate_gps_tracker),
                    OperationsMenuItem(VIEW_TRACKER_IN_MAPS, R.drawable.view_tracker_in_maps),
                )
            }
            ACADEMICS -> {
                menuItems = listOf(
                    OperationsMenuItem(CURRICULUM_MANAGEMENT, R.drawable.curriculum_management),
                    OperationsMenuItem(CCE_SCHOLASTIC, R.drawable.cce_scholastic),
                    OperationsMenuItem(ACADEMIC_PROJECT, R.drawable.academic_project),
                    OperationsMenuItem(KNOWLEDGE_MANAGEMENT, R.drawable.knowledge_management),
                    OperationsMenuItem(EXAMINATION_MANAGEMENT, R.drawable.examination_management)
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

                    AUTO_ATTENDANCE -> currentNavController.navigate(R.id.onBoardAttendanceSelectRouteFragment)
                    MANUAL_ATTENDANCE -> currentNavController.navigate(R.id.onBoardManualAttendanceFragment)

                    INITIATE_TRACKER -> currentNavController.navigate(R.id.initiateGpsTrackerFragment)
                    VIEW_TRACKER_IN_MAPS -> currentNavController.navigate(R.id.viewGpsMapFragment)


                }
            })
        }
    }
}