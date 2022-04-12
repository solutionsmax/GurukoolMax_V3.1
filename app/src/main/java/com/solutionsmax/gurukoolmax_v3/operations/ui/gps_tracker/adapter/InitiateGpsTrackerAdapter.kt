package com.solutionsmax.gurukoolmax_v3.operations.ui.gps_tracker.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.databinding.InitiateGpsTrackerItemsBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.FleetBusPickupPointsList

class InitiateGpsTrackerAdapter(
    private val listItems: List<FleetBusPickupPointsList>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = InitiateGpsTrackerItemsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InitiateGpsTrackerViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val initiateGpsTrackerViewHolder: InitiateGpsTrackerViewHolder =
            holder as InitiateGpsTrackerViewHolder
        initiateGpsTrackerViewHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class InitiateGpsTrackerViewHolder(
        private val binding: InitiateGpsTrackerItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(items: FleetBusPickupPointsList, clickListener: OnItemClick) {
            binding.fleetBusStop = items
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
        fun onEditClick(items: FleetBusPickupPointsList) = clickListener(items.id)
    }
}