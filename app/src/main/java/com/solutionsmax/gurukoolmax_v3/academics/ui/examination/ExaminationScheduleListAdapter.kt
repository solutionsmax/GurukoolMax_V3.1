package com.solutionsmax.gurukoolmax_v3.academics.ui.examination

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.RetrieveExamScheduleItems
import com.solutionsmax.gurukoolmax_v3.core.common.WorkflowStatus
import com.solutionsmax.gurukoolmax_v3.core.extension.invisible
import com.solutionsmax.gurukoolmax_v3.core.extension.visible
import com.solutionsmax.gurukoolmax_v3.databinding.ExaminationScheduleItemBinding

class ExaminationScheduleListAdapter(
    private val listItems: List<RetrieveExamScheduleItems>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = ExaminationScheduleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExaminationScheduleListViewHolder(layout)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val examScheduleViewHolder: ExaminationScheduleListViewHolder =
            holder as ExaminationScheduleListViewHolder
        examScheduleViewHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class ExaminationScheduleListViewHolder(
        private val binding: ExaminationScheduleItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(items: RetrieveExamScheduleItems, clickListener: OnItemClick) {
            binding.examSchedule = items
            binding.clickListener = clickListener
            binding.lblStatus.text = WorkflowStatus.setStatus(items.iWorkflowStatusID)
            binding.lblStatus.setBackgroundColor(getStatusColors(items.iWorkflowStatusID))
            if (items.iWorkflowStatusID == 3 || items.iWorkflowStatusID == 4) {
                binding.lblEdit.invisible()
            } else {
                binding.lblEdit.visible()
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        private fun getStatusColors(iWorkflowStatusID: Int): Int {
            return when (iWorkflowStatusID) {
                4 -> itemView.context.getColor(R.color.green_500)
                1 -> itemView.context.getColor(R.color.grey_500)
                else -> {
                    itemView.context.getColor(R.color.blue_500)
                }
            }
        }
    }

    class OnItemClick(val clickListener: (id: Int) -> Unit) {
        fun onEditClick(items: RetrieveExamScheduleItems) = clickListener(items.id)
    }

}