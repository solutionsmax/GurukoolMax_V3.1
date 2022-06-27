package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.map_subject_to_faculty.PopulateMapSubjectToFacultyItem
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.SetupLearningSessionInfoFragment
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class SLSFaculty(
    private val populateMasterListItem: List<PopulateMapSubjectToFacultyItem>,
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

        fun bind(items: PopulateMapSubjectToFacultyItem, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sFacultyName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                SetupLearningSessionInfoFragment.cboFaculty!!.text = items.sFacultyName
                SetupLearningSessionInfoFragment.iFacultyID = items.iFacultyID
                SetupLearningSessionInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateMapSubjectToFacultyItem) -> Unit) {
        fun onEditClick(items: PopulateMapSubjectToFacultyItem) = clickListener(items)
    }
}