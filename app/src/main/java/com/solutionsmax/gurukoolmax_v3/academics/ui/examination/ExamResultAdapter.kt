package com.solutionsmax.gurukoolmax_v3.academics.ui.examination

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_result.RetrieveExamResultsItems
import com.solutionsmax.gurukoolmax_v3.core.common.WorkflowStatus
import com.solutionsmax.gurukoolmax_v3.databinding.ExamResultItemBinding

class ExamResultAdapter(
    private val listItems: List<RetrieveExamResultsItems>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class OnItemClick(val clickListener: (id: Int) -> Unit) {
        fun onEditClick(items: RetrieveExamResultsItems) = clickListener(items.id)
    }

    class ExamResultViewHolder(
        private val binding: ExamResultItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(items: RetrieveExamResultsItems, clickListener: OnItemClick) {
            binding.examResult = items
            binding.clickListener = clickListener
            binding.lblStatus.text = WorkflowStatus.setStatus(items.iWorkflowStatusID)
            binding.lblStatus.setBackgroundColor(getStatusColors(items.iWorkflowStatusID))
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout =
            ExamResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExamResultViewHolder(layout)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val examResultHolder: ExamResultViewHolder = holder as ExamResultViewHolder
        examResultHolder.bind(listItems[position], clickListener)
    }


    override fun getItemCount(): Int = listItems.size
}