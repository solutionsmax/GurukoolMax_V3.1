package com.solutionsmax.gurukoolmax_v3.academics.ui.km.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.KMPostRepositoryInfoFragment
import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class KM_ContentTypes(
    private val populateMasterListItem: List<PopulateMasterListItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KM_ContentTypesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: KM_ContentTypesViewHolder =
            holder as KM_ContentTypesViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class KM_ContentTypesViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateMasterListItem, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                KMPostRepositoryInfoFragment.cboContentType!!.text = items.sName
                KMPostRepositoryInfoFragment.iContentTypeID = items.id
                KMPostRepositoryInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateMasterListItem) -> Unit) {
        fun onEditClick(items: PopulateMasterListItem) = clickListener(items)
    }
}