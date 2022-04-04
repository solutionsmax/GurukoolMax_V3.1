package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.fuel_log.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.PopulateRegisteredFleetList
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.fuel_log.FleetFuelLogInfoFragment

class FFLVehicleNameSpinnerAdapter(
    private val populateRegisteredFleetList: List<PopulateRegisteredFleetList>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FFLVehicleNameSpinnerViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: FFLVehicleNameSpinnerViewHolder =
            holder as FFLVehicleNameSpinnerViewHolder
        rfManufactureYearHolder.bind(populateRegisteredFleetList[position], clickListener)
    }

    override fun getItemCount(): Int = populateRegisteredFleetList.size

    class  FFLVehicleNameSpinnerViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateRegisteredFleetList, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sVehicleName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                FleetFuelLogInfoFragment.lblVehicleName!!.text = items.sVehicleName
                FleetFuelLogInfoFragment.iVehicleID = items.id
                FleetFuelLogInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateRegisteredFleetList) -> Unit) {
        fun onEditClick(items: PopulateRegisteredFleetList) = clickListener(items)
    }
}