package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.PopulateCurriculumSessionTopicList
import com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.SetupLearningSessionInfoFragment
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class SLSTopicList(
    private val populateMasterListItem: List<PopulateCurriculumSessionTopicList>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SLSTopicListViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: SLSTopicListViewHolder =
            holder as SLSTopicListViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class SLSTopicListViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateCurriculumSessionTopicList, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sSectionTitle
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                SetupLearningSessionInfoFragment.cboTopic!!.text = items.sSectionTitle
                SetupLearningSessionInfoFragment.iTopicID = items.id
                SetupLearningSessionInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateCurriculumSessionTopicList) -> Unit) {
        fun onEditClick(items: PopulateCurriculumSessionTopicList) = clickListener(items)
    }
}