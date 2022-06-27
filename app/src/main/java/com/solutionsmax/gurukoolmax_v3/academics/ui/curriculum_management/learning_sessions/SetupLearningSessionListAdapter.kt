package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.learning_session.RetrieveLearningSessionItem
import com.solutionsmax.gurukoolmax_v3.core.common.WorkflowStatus
import com.solutionsmax.gurukoolmax_v3.core.extension.invisible
import com.solutionsmax.gurukoolmax_v3.core.extension.visible
import com.solutionsmax.gurukoolmax_v3.databinding.SetupLearningSessionItemBinding

class SetupLearningSessionListAdapter(
    private val listItems: List<RetrieveLearningSessionItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class OnItemClick(val clickListener: (id: Int) -> Unit) {
        fun onEditClick(items: RetrieveLearningSessionItem) = clickListener(items.id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SetupLearningSessionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SetupLearningSessionListViewHolder(layout)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val setupLearningSessionListViewHolder: SetupLearningSessionListViewHolder =
            holder as SetupLearningSessionListViewHolder
        setupLearningSessionListViewHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class SetupLearningSessionListViewHolder(
        private val binding: SetupLearningSessionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(items: RetrieveLearningSessionItem, click: OnItemClick) {
            binding.learningSession = items
            binding.clickListener = click
            binding.lblStatus.text = WorkflowStatus.setStatus(items.iWorkflowStatusID)
            if (items.iWorkflowStatusID == 3 || items.iWorkflowStatusID == 4) {
                binding.lblEdit.invisible()
            } else {
                binding.lblEdit.visible()
            }
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