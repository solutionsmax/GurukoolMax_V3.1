package com.solutionsmax.gurukoolmax_v3.academics.ui.examination.spinner_adapter.exam_setup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.ui.examination.ExaminationSetupInfoFragment
import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class ExamSetupBoard(
    private val populateMasterListItem: List<PopulateMasterListItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExamSetupBoardViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: ExamSetupBoardViewHolder =
            holder as ExamSetupBoardViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class ExamSetupBoardViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateMasterListItem, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                ExaminationSetupInfoFragment.cboBoard!!.text = items.sName
                ExaminationSetupInfoFragment.iBoardID = items.id
                ExaminationSetupInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateMasterListItem) -> Unit) {
        fun onEditClick(items: PopulateMasterListItem) = clickListener(items)
    }
}