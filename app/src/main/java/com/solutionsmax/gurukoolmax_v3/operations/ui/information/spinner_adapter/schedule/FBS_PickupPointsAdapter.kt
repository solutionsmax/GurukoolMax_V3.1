package com.solutionsmax.gurukoolmax_v3.operations.ui.information.spinner_adapter.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.PopulateFleetBusStopItems
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetScheduleInfoFragment

class FBS_PickupPointsAdapter(
    private val populateMasterListItem: List<PopulateFleetBusStopItems>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FBS_PickupPointsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: FBS_PickupPointsViewHolder =
            holder as FBS_PickupPointsViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class FBS_PickupPointsViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateFleetBusStopItems, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sRouteName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                FleetScheduleInfoFragment.lblPickupPoint!!.text = items.sBusStopName
                FleetScheduleInfoFragment.iPickupPoint = items.iBusRouteID
                FleetScheduleInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateFleetBusStopItems) -> Unit) {
        fun onEditClick(items: PopulateFleetBusStopItems) = clickListener(items)
    }
}