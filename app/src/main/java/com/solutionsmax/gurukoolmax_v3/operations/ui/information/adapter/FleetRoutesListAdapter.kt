package com.solutionsmax.gurukoolmax_v3.operations.ui.information.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.databinding.FleetRoutesListItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.FleetBusRouteList

class FleetRoutesListAdapter(
    private val listItems: List<FleetBusRouteList>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            FleetRoutesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FleetRoutesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val fleetHolder: FleetRoutesViewHolder = holder as FleetRoutesViewHolder
        fleetHolder.bind(listItems[position])
    }

    override fun getItemCount(): Int = listItems.size

    class FleetRoutesViewHolder(
        private val binding: FleetRoutesListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: FleetBusRouteList) {
            binding.routeItem = items
        }
    }
}