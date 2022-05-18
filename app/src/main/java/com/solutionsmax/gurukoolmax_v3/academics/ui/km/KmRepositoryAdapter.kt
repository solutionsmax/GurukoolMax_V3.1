package com.solutionsmax.gurukoolmax_v3.academics.ui.km

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.km.RetrieveKMInfo
import com.solutionsmax.gurukoolmax_v3.core.common.WorkflowStatus
import com.solutionsmax.gurukoolmax_v3.databinding.KmRepositoryItemBinding

class KmRepositoryAdapter(
    private val listItems: List<RetrieveKMInfo>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout =
            KmRepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KmRepositoryViewHolder(layout)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val kmRepositoryHolder: KmRepositoryViewHolder = holder as KmRepositoryViewHolder
        kmRepositoryHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class KmRepositoryViewHolder(private val binding: KmRepositoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(item: RetrieveKMInfo, clickListener: OnItemClick) {
            binding.kmItem = item
            binding.clickListener = clickListener
            binding.lblStatus.text = WorkflowStatus.setStatus(item.iWorkflowStatusID)
            binding.lblStatus.setBackgroundColor(getStatusColors(item.iWorkflowStatusID))
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
        fun onEditClick(items: RetrieveKMInfo) = clickListener(items.id)
    }
}