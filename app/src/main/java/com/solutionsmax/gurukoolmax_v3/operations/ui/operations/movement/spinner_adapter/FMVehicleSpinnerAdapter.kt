package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.movement.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.PopulateRegisteredFleetList
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.movement.RegisteredFleetMovementInfoFragment

class FMVehicleSpinnerAdapter(
    private val populateRegisteredFleetList: List<PopulateRegisteredFleetList>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FMVehicleSpinnerViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: FMVehicleSpinnerViewHolder =
            holder as FMVehicleSpinnerViewHolder
        rfManufactureYearHolder.bind(populateRegisteredFleetList[position], clickListener)
    }

    override fun getItemCount(): Int = populateRegisteredFleetList.size

    class FMVehicleSpinnerViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateRegisteredFleetList, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sVehicleName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                RegisteredFleetMovementInfoFragment.lblVehicleName!!.text = items.sVehicleName
                RegisteredFleetMovementInfoFragment.iVehicleID = items.id
                RegisteredFleetMovementInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateRegisteredFleetList) -> Unit) {
        fun onEditClick(items: PopulateRegisteredFleetList) = clickListener(items)
    }
}