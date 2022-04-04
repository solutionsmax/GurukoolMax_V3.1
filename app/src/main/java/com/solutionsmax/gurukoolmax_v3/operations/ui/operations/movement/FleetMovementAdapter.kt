package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.movement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.WorkflowStatus
import com.solutionsmax.gurukoolmax_v3.databinding.FleetMovementListItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_movement.FleetMovementRetrieveItem

class FleetMovementAdapter(
    private val listItems: List<FleetMovementRetrieveItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout =
            FleetMovementListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FleetMovementViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val fleetMovementHolder: FleetMovementViewHolder = holder as FleetMovementViewHolder
        fleetMovementHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class FleetMovementViewHolder(
        private val binding: FleetMovementListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: FleetMovementRetrieveItem, clickListener: OnItemClick) {
            binding.fleetMovement = items
            if (items.iClosingReading == -1) {
                binding.lblClosingReading.text =
                    itemView.context.getString(R.string.not_application)
            } else {
                binding.lblClosingReading.text = items.iClosingReading.toString()
            }
            binding.clickListener = clickListener
            binding.lblStatus.text = WorkflowStatus.setStatus(items.iWorkflowStatusID)
        }
    }

    class OnItemClick(val clickListener: (id: Int) -> Unit) {
        fun onEditClick(items: FleetMovementRetrieveItem) = clickListener(items.id)
    }
}