package com.solutionsmax.gurukoolmax_v3.operations.ui.information.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.databinding.FleetSchedulePickupItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetPickupScheduleList

class FleetSchedulePickupAdapter(
    private val listItems: List<FleetPickupScheduleList>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = FleetSchedulePickupItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FleetSchedulePickupViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val fleetPickupViewHolder: FleetSchedulePickupViewHolder =
            holder as FleetSchedulePickupViewHolder
        fleetPickupViewHolder.bind(listItems[position])
    }

    override fun getItemCount(): Int = listItems.size

    class FleetSchedulePickupViewHolder(
        private val binding: FleetSchedulePickupItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: FleetPickupScheduleList) {
            binding.pickupScheduleItem = items
        }
    }
}