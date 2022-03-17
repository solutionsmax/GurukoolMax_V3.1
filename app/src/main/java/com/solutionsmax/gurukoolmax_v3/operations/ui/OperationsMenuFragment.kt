package com.solutionsmax.gurukoolmax_v3.operations.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentOperationsMenuBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.FLEET_FEE_DEFAULTERS
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.FLEET_GPS_TRACKER
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.FLEET_INFORMATION
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.FLEET_OPERATIONS
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.FLEET_REGISTRY
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.ON_BOARD_ATTENDANCE
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationsMenuItem
import com.solutionsmax.gurukoolmax_v3.operations.ui.adapter.OperationsMenuAdapter

class OperationsMenuFragment : BaseFragment() {

    private lateinit var binding: FragmentOperationsMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOperationsMenuBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.operationsToolbar.apply {
            title = getString(R.string.home)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
        }

        val menuItems = listOf(
            OperationsMenuItem(FLEET_OPERATIONS, R.drawable.fleet_operations),
            OperationsMenuItem(ON_BOARD_ATTENDANCE, R.drawable.on_board_attendance),
            OperationsMenuItem(FLEET_GPS_TRACKER, R.drawable.fleet_gps_tracker),
            OperationsMenuItem(FLEET_FEE_DEFAULTERS, R.drawable.fleet_fee_defaulter),
            OperationsMenuItem(FLEET_INFORMATION, R.drawable.fleet_info),
            OperationsMenuItem(FLEET_REGISTRY, R.drawable.fleet_register)
        )

        with(binding.operationsMenu) {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = OperationsMenuAdapter(menuItems, OperationsMenuAdapter.OnItemClick {
                when (it) {
                    FLEET_INFORMATION -> currentNavController.navigate(
                        R.id.operationsSubMenuFragment,
                        bundleOf("menu" to it)
                    )
                    ON_BOARD_ATTENDANCE -> currentNavController.navigate(R.id.onBoardAttendanceFragment)
                    FLEET_OPERATIONS -> currentNavController.navigate(
                        R.id.operationsSubMenuFragment,
                        bundleOf("menu" to it)
                    )
                }
            })
        }
    }

}