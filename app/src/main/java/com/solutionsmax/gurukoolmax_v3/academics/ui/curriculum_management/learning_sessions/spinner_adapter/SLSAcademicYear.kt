package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.SetupLearningSessionInfoFragment
import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class SLSAcademicYear(
    private val populateMasterListItem: List<PopulateMasterListItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SLSAcademicYearViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: SLSAcademicYearViewHolder =
            holder as SLSAcademicYearViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class SLSAcademicYearViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateMasterListItem, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                SetupLearningSessionInfoFragment.cboAcademicYear!!.text = items.sName
                SetupLearningSessionInfoFragment.iAcademicYearID = items.id
                SetupLearningSessionInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateMasterListItem) -> Unit) {
        fun onEditClick(items: PopulateMasterListItem) = clickListener(items)
    }
}