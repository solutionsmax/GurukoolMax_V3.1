package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectProgramList
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.SetupLearningSessionInfoFragment
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class SLSSubject(
    private val populateMasterListItem: List<PopulateSubjectProgramList>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SLSSubjectViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: SLSSubjectViewHolder =
            holder as SLSSubjectViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class SLSSubjectViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateSubjectProgramList, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sSubjectName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                SetupLearningSessionInfoFragment.cboSubject!!.text = items.sSubjectName
                SetupLearningSessionInfoFragment.iSubjectID = items.id
                SetupLearningSessionInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateSubjectProgramList) -> Unit) {
        fun onEditClick(items: PopulateSubjectProgramList) = clickListener(items)
    }
}