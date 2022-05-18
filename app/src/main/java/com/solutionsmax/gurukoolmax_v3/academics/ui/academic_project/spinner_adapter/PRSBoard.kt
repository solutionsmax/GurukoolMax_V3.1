package com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project.ProjectsRepositorySearchFragment
import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class PRSBoard(
    private val populateMasterListItem: List<PopulateMasterListItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PRSBoardViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: PRSBoardViewHolder =
            holder as PRSBoardViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class PRSBoardViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateMasterListItem, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                ProjectsRepositorySearchFragment.cboBoard!!.text = items.sName
                ProjectsRepositorySearchFragment.iAcademicBoard = items.id
                ProjectsRepositorySearchFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateMasterListItem) -> Unit) {
        fun onEditClick(items: PopulateMasterListItem) = clickListener(items)
    }
}