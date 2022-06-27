package com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.project.RetrieveAcademicProjectItem
import com.solutionsmax.gurukoolmax_v3.core.common.WorkflowStatus
import com.solutionsmax.gurukoolmax_v3.core.extension.invisible
import com.solutionsmax.gurukoolmax_v3.core.extension.visible
import com.solutionsmax.gurukoolmax_v3.databinding.ProjectRepositoryListItemBinding

class ProjectRepositoryListAdapter(
    private val listItems: List<RetrieveAcademicProjectItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = ProjectRepositoryListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProjectRepositoryListViewHolder(layout)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val projectHolder: ProjectRepositoryListViewHolder =
            holder as ProjectRepositoryListViewHolder
        projectHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class ProjectRepositoryListViewHolder(
        private val binding: ProjectRepositoryListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(item: RetrieveAcademicProjectItem, clickListener: OnItemClick) {
            binding.projectItems = item
            binding.clickListener = clickListener
            binding.lblStatus.text = WorkflowStatus.setStatus(item.iWorkflowStatusID)
            binding.lblStatus.setBackgroundColor(getStatusColors(item.iWorkflowStatusID))
            if (item.iWorkflowStatusID == 3 || item.iWorkflowStatusID == 4) {
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
        fun onEditClick(items: RetrieveAcademicProjectItem) = clickListener(items.id)
    }
}