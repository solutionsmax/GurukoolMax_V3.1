package com.solutionsmax.gurukoolmax_v3.academics.ui.examination.spinner_adapter.exam_setup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.PopulateExamScheduleListItem
import com.solutionsmax.gurukoolmax_v3.academics.ui.examination.ExaminationSetupInfoFragment
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class ExamSetupExamSchedule(
    private val populateMasterListItem: List<PopulateExamScheduleListItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExamSetupExamScheduleViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: ExamSetupExamScheduleViewHolder =
            holder as ExamSetupExamScheduleViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class ExamSetupExamScheduleViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateExamScheduleListItem, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sScheduleName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                ExaminationSetupInfoFragment.cboExaminationSchedule!!.text = items.sScheduleName
                ExaminationSetupInfoFragment.iExaminationScheduleID = items.id
                ExaminationSetupInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateExamScheduleListItem) -> Unit) {
        fun onEditClick(items: PopulateExamScheduleListItem) = clickListener(items)
    }
}