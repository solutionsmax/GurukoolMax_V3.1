package com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.grade_system

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.cce_scholastic.RetrieveCceScholasticItem
import com.solutionsmax.gurukoolmax_v3.core.common.WorkflowStatus
import com.solutionsmax.gurukoolmax_v3.databinding.ScholasticGradeSystemAdapterBinding

class ScholasticGradeSystemAdapter(
    private val listItems: List<RetrieveCceScholasticItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = ScholasticGradeSystemAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ScholasticGradeSystemViewHolder(layout)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val scholasticHolder: ScholasticGradeSystemViewHolder =
            holder as ScholasticGradeSystemViewHolder
        scholasticHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class ScholasticGradeSystemViewHolder(
        private val binding: ScholasticGradeSystemAdapterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(items: RetrieveCceScholasticItem, clickListener: OnItemClick) {
            binding.gradeSystem = items
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

    class OnItemClick(val clickListener: (id: Int) -> Unit) {
        fun onEditClick(items: RetrieveCceScholasticItem) = clickListener(items.id)
    }
}