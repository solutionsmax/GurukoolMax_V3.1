package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common.PopulateClassItems
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.SetupLearningSessionInfoFragment
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class SLSClass(
    private val populateMasterListItem: List<PopulateClassItems>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SLSClassViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: SLSClassViewHolder =
            holder as SLSClassViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class SLSClassViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateClassItems, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sClassStandard
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                SetupLearningSessionInfoFragment.cboClass!!.text = items.sClassStandard
                SetupLearningSessionInfoFragment.iClassId = items.iClassStandardID
                SetupLearningSessionInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateClassItems) -> Unit) {
        fun onEditClick(items: PopulateClassItems) = clickListener(items)
    }
}