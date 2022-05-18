package com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.recording

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.RetrieveScholasticRecordingItems
import com.solutionsmax.gurukoolmax_v3.core.common.WorkflowStatus
import com.solutionsmax.gurukoolmax_v3.databinding.CceScholasticItemBinding

class CceScholasticListAdapter(
    private val listItems: List<RetrieveScholasticRecordingItems>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout =
            CceScholasticItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CceScholasticListViewHolder(layout)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cceHolder: CceScholasticListViewHolder = holder as CceScholasticListViewHolder
        cceHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class CceScholasticListViewHolder(
        private val binding: CceScholasticItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(items: RetrieveScholasticRecordingItems, click: OnItemClick) {
            binding.cceRecording = items
            binding.clickListner = click
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
        fun onEditClick(items: RetrieveScholasticRecordingItems) = clickListener(items.id)
    }
}