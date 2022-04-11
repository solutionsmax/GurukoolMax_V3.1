package com.solutionsmax.gurukoolmax_v3.operations.ui.information.spinner_adapter.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetScheduleInfoFragment

class FBS_MinutesAdapter(
    private val populateMasterListItem: List<PopulateMasterListItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FBS_HoursViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: FBS_HoursViewHolder =
            holder as FBS_HoursViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class FBS_HoursViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateMasterListItem, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                FleetScheduleInfoFragment.lblMM!!.text = items.sName
                FleetScheduleInfoFragment.iMm = items.id
                FleetScheduleInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateMasterListItem) -> Unit) {
        fun onEditClick(items: PopulateMasterListItem) = clickListener(items)
    }
}