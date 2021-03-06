package com.solutionsmax.gurukoolmax_v3.operations.ui.information.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.databinding.FleetRoutesListItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.FleetBusRouteList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetRegisterRetrieveListItem

class FleetRoutesListAdapter(
    private val listItems: List<FleetBusRouteList>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            FleetRoutesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FleetRoutesViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val fleetHolder: FleetRoutesViewHolder = holder as FleetRoutesViewHolder
        fleetHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class FleetRoutesViewHolder(
        private val binding: FleetRoutesListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(items: FleetBusRouteList, clickListener: OnItemClick) {
            binding.routeItem = items
            binding.clickListener = clickListener
            binding.lblStatus.setBackgroundColor(getStatusColors(items.iWorkflowStatusID))
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun getStatusColors(iWorkflowStatusID: Int): Int {
            return when (iWorkflowStatusID) {
                4 -> itemView.context.getColor(R.color.green_500)
                1 -> itemView.context.getColor(R.color.grey_500)
                else -> {
                    itemView.context.getColor(R.color.blue_500)
                }
            }
        }
    }

    class OnItemClick(val clickListener: (id: Int) -> Unit) {
        fun onEditClick(items: FleetBusRouteList) = clickListener(items.id)
    }
}