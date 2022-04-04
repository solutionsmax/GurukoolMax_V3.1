package com.solutionsmax.gurukoolmax_v3.operations.ui.information.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.databinding.FleetBusStopListItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetBusPickupPointsList
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register.RegisteredFleetAdapter

class FleetBusStopListAdapter(
    private val listItems: List<FleetBusPickupPointsList>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            FleetBusStopListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FleetBusStopViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val fleetBusViewHolder: FleetBusStopViewHolder = holder as FleetBusStopViewHolder
        fleetBusViewHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class FleetBusStopViewHolder(
        private val binding: FleetBusStopListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: FleetBusPickupPointsList, clickListener: OnItemClick) {
            binding.fleetBusStop = items
        }
    }

    class OnItemClick(val clickListener: (id: Int) -> Unit) {
        fun onEditClick(items: FleetBusPickupPointsList) = clickListener(items.id)
    }
}