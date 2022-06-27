package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectProgramList
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus.CurriculumSetupInfoFragment
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class CS_Subject(
    private val populateMasterListItem: List<PopulateSubjectProgramList>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CS_SubjectViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: CS_SubjectViewHolder =
            holder as CS_SubjectViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class CS_SubjectViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateSubjectProgramList, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sSubjectName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                CurriculumSetupInfoFragment.cboSubject!!.text = items.sSubjectName
                CurriculumSetupInfoFragment.iSubjectID = items.id
                CurriculumSetupInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateSubjectProgramList) -> Unit) {
        fun onEditClick(items: PopulateSubjectProgramList) = clickListener(items)
    }
}