package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.subject_management.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectProgramList
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.subject_management.SubjectManagementInfoFragment
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class SM_Subject(
    private val populateMasterListItem: List<PopulateSubjectProgramList>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SM_SubjectViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: SM_SubjectViewHolder =
            holder as SM_SubjectViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class SM_SubjectViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateSubjectProgramList, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sSubjectName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                SubjectManagementInfoFragment.cboSubject!!.text = items.sSubjectName
                SubjectManagementInfoFragment.iSubjectID = items.id
                SubjectManagementInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateSubjectProgramList) -> Unit) {
        fun onEditClick(items: PopulateSubjectProgramList) = clickListener(items)
    }
}