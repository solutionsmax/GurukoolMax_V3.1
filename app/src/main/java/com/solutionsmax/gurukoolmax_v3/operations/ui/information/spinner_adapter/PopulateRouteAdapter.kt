package com.solutionsmax.gurukoolmax_v3.operations.ui.information.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.PopulateBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.FleetBusStopInfoFragment

class PopulateRouteAdapter(
    private val populateMasterListItem: List<PopulateBusRoutesItems>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopulateRouteViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: PopulateRouteViewHolder =
            holder as PopulateRouteViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class PopulateRouteViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateBusRoutesItems, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sRouteName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                FleetBusStopInfoFragment.lblRoute!!.text = items.sRouteName
                FleetBusStopInfoFragment.iRouteID = items.id
                FleetBusStopInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateBusRoutesItems) -> Unit) {
        fun onEditClick(items: PopulateBusRoutesItems) = clickListener(items)
    }
}