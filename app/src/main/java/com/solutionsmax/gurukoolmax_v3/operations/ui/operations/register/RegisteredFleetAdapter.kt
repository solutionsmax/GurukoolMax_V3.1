package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.databinding.RegisteredFleetItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetRegisterRetrieveListItem

class RegisteredFleetAdapter(
    private val listItems: List<FleetRegisterRetrieveListItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout =
            RegisteredFleetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RegisteredFleetViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val registeredFleet: RegisteredFleetViewHolder = holder as RegisteredFleetViewHolder
        registeredFleet.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class RegisteredFleetViewHolder(private val binding: RegisteredFleetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(items: FleetRegisterRetrieveListItem, clickListener: OnItemClick) {
            binding.fleetItems = items
            binding.clickListener = clickListener
            when (items.iWorkflowStatusID) {
                // TODO - create a constant for status
                1 -> binding.lblStatus.text = "Approved"
            }
        }
    }

    class OnItemClick(val clickListener: (id: Int) -> Unit) {
        fun onEditClick(items: FleetRegisterRetrieveListItem) = clickListener(items.id)
    }
}