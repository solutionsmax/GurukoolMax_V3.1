package com.solutionsmax.gurukoolmax_v3.academics.ui.km.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common.PopulateClassItems
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus.CurriculumSetupInfoFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.KMPostRepositoryInfoFragment
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class KM_Class(
    private val populateMasterListItem: List<PopulateClassItems>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KM_ClassViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: KM_ClassViewHolder =
            holder as KM_ClassViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class KM_ClassViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateClassItems, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sClassStandard
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                KMPostRepositoryInfoFragment.cboClass!!.text = items.sClassStandard
                KMPostRepositoryInfoFragment.iClassID = items.iClassStandardID
                KMPostRepositoryInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateClassItems) -> Unit) {
        fun onEditClick(items: PopulateClassItems) = clickListener(items)
    }
}