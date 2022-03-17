package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.fuel_log

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.databinding.FuelLogsListItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log.FuelLogsRetrieveItems

class FleetFuelLogsAdapter(
    private val listItems: List<FuelLogsRetrieveItems>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            FuelLogsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FleetFuelLogsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val fuelViewHolder: FleetFuelLogsViewHolder = holder as FleetFuelLogsViewHolder
        fuelViewHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class FleetFuelLogsViewHolder(
        private val binding: FuelLogsListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: FuelLogsRetrieveItems, clickListener: OnItemClick) {
            binding.fuelLogs = items
            binding.clickListener = clickListener
            when (items.iWorkflowStatusID) {
                // TODO - create a constant for status
                1 -> binding.lblStatus.text = "Approved"
            }
        }
    }

    class OnItemClick(val clickListener: (id: Int) -> Unit) {
        fun onEditClick(items: FuelLogsRetrieveItems) = clickListener(items.id)
    }
}