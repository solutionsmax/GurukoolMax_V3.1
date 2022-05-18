package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.RetrieveCurriculumInfoItems
import com.solutionsmax.gurukoolmax_v3.core.common.WorkflowStatus
import com.solutionsmax.gurukoolmax_v3.databinding.CurriculumSetupListItemBinding

class CurriculumSetupListAdapter(
    private val listItems: List<RetrieveCurriculumInfoItems>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class OnItemClick(val clickListener: (id: Int) -> Unit) {
        fun onEditClick(items: RetrieveCurriculumInfoItems) = clickListener(items.id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = CurriculumSetupListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CurriculumSetupViewHolder(layout)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val curriculumHolder: CurriculumSetupViewHolder = holder as CurriculumSetupViewHolder
        curriculumHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class CurriculumSetupViewHolder(
        private val binding: CurriculumSetupListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(items: RetrieveCurriculumInfoItems, clickListener: OnItemClick) {
            binding.curriculumItem = items
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
}