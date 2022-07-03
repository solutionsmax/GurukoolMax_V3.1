package com.solutionsmax.gurukoolmax_v3.academics.ui.km.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectProgramList
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus.CurriculumSetupInfoFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.KMPostRepositoryInfoFragment
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class KM_Subject(
    private val populateMasterListItem: List<PopulateSubjectList>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KM_SubjectViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: KM_SubjectViewHolder =
            holder as KM_SubjectViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class KM_SubjectViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateSubjectList, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sSubjectName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                KMPostRepositoryInfoFragment.cboSubject!!.text = items.sSubjectName
                KMPostRepositoryInfoFragment.iSubjectID = items.iSubjectID
                KMPostRepositoryInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateSubjectList) -> Unit) {
        fun onEditClick(items: PopulateSubjectList) = clickListener(items)
    }
}