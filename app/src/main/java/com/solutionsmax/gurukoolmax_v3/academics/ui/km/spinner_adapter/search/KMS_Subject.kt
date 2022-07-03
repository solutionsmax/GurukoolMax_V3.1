package com.solutionsmax.gurukoolmax_v3.academics.ui.km.spinner_adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectProgramList
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus.CurriculumSetupInfoFragment
import com.solutionsmax.gurukoolmax_v3.academics.ui.km.KMSearchRepositoryFragment
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class KMS_Subject(
    private val populateMasterListItem: List<PopulateSubjectList>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KMS_SubjectViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: KMS_SubjectViewHolder =
            holder as KMS_SubjectViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class KMS_SubjectViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateSubjectList, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sSubjectName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                KMSearchRepositoryFragment.cboSubject!!.text = items.sSubjectName
                KMSearchRepositoryFragment.iSubjectID = items.iSubjectID
                KMSearchRepositoryFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateSubjectList) -> Unit) {
        fun onEditClick(items: PopulateSubjectList) = clickListener(items)
    }
}