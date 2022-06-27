package com.solutionsmax.gurukoolmax_v3.academics.ui.examination

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_setup.RetrieveExamSetupDetails
import com.solutionsmax.gurukoolmax_v3.core.common.WorkflowStatus
import com.solutionsmax.gurukoolmax_v3.core.extension.invisible
import com.solutionsmax.gurukoolmax_v3.core.extension.visible
import com.solutionsmax.gurukoolmax_v3.databinding.ExaminationSetupListItemBinding

class ExaminationSetupListAdapter(
    private val listItems: List<RetrieveExamSetupDetails>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ExaminationSetupViewHolder(
        private val binding: ExaminationSetupListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(items: RetrieveExamSetupDetails, clickListener: OnItemClick) {
            binding.examSetup = items
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
        fun onEditClick(items: RetrieveExamSetupDetails) = clickListener(items.id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = ExaminationSetupListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExaminationSetupViewHolder(layout)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val examHolder: ExaminationSetupViewHolder = holder as ExaminationSetupViewHolder
        examHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size
}