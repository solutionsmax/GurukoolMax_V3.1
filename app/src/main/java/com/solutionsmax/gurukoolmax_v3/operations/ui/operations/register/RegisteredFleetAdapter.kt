package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.WorkflowStatus
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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val registeredFleet: RegisteredFleetViewHolder = holder as RegisteredFleetViewHolder
        registeredFleet.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class RegisteredFleetViewHolder(private val binding: RegisteredFleetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(items: FleetRegisterRetrieveListItem, clickListener: OnItemClick) {
            binding.fleetItems = items
            binding.clickListener = clickListener
            binding.lblStatus.text = WorkflowStatus.setStatus(items.iWorkflowStatusID)
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
        fun onEditClick(items: FleetRegisterRetrieveListItem) = clickListener(items.id)
    }
}