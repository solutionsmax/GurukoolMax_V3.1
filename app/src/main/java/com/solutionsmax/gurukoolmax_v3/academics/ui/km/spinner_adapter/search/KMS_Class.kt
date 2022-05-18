package com.solutionsmax.gurukoolmax_v3.academics.ui.km.spinner_adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common.PopulateClassItems
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.KMSearchRepositoryFragment
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class KMS_Class(
    private val populateMasterListItem: List<PopulateClassItems>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KMS_ClassViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: KMS_ClassViewHolder =
            holder as KMS_ClassViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class KMS_ClassViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateClassItems, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sClassStandard
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                KMSearchRepositoryFragment.cboClass!!.text = items.sClassStandard
                KMSearchRepositoryFragment.iClassID = items.iClassStandardID
                KMSearchRepositoryFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateClassItems) -> Unit) {
        fun onEditClick(items: PopulateClassItems) = clickListener(items)
    }
}