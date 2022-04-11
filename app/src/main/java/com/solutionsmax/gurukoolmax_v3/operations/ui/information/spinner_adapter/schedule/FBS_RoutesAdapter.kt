package com.solutionsmax.gurukoolmax_v3.operations.ui.information.spinner_adapter.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.PopulateBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetBusStopInfoFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetScheduleInfoFragment

class FBS_RoutesAdapter(
    private val populateMasterListItem: List<PopulateBusRoutesItems>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FBS_RoutesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: FBS_RoutesViewHolder =
            holder as FBS_RoutesViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class FBS_RoutesViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateBusRoutesItems, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sRouteName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                FleetScheduleInfoFragment.lblRoute!!.text = items.sRouteName
                FleetScheduleInfoFragment.iRouteID = items.id
                FleetScheduleInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateBusRoutesItems) -> Unit) {
        fun onEditClick(items: PopulateBusRoutesItems) = clickListener(items)
    }
}