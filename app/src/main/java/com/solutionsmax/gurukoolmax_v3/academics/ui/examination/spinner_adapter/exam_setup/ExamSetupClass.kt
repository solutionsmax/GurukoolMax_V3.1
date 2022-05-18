package com.solutionsmax.gurukoolmax_v3.academics.ui.examination.spinner_adapter.exam_setup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common.PopulateClassItems
import com.solutionsmax.gurukoolmax_v3.academics.ui.examination.ExaminationSetupInfoFragment
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class ExamSetupClass(
    private val populateMasterListItem: List<PopulateClassItems>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExamSetupClassViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: ExamSetupClassViewHolder =
            holder as ExamSetupClassViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class ExamSetupClassViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateClassItems, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sClassStandard
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                ExaminationSetupInfoFragment.cboClass!!.text = items.sClassStandard
                ExaminationSetupInfoFragment.iClassID = items.iClassStandardID
                ExaminationSetupInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateClassItems) -> Unit) {
        fun onEditClick(items: PopulateClassItems) = clickListener(items)
    }
}