package com.solutionsmax.gurukoolmax_v3.operations.ui.information.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.databinding.FleetSchedulePickupItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetPickupScheduleList
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.FleetBusRouteList

class FleetSchedulePickupAdapter(
    private val listItems: List<FleetPickupScheduleList>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = FleetSchedulePickupItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FleetSchedulePickupViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val fleetPickupViewHolder: FleetSchedulePickupViewHolder =
            holder as FleetSchedulePickupViewHolder
        fleetPickupViewHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class FleetSchedulePickupViewHolder(
        private val binding: FleetSchedulePickupItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(items: FleetPickupScheduleList, clickListener: OnItemClick) {
            binding.pickupScheduleItem = items
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
        fun onEditClick(items: FleetPickupScheduleList) = clickListener(items.id)
    }
}