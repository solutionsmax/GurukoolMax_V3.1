package com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.recording.spinner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.scholastic_recording.PopulateStudentScholasticGradingItem
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.recording.CceScholasticInfoFragment
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class CCEStudent(
    private val populateMasterListItem: List<PopulateStudentScholasticGradingItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CS_ClassViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rfManufactureYearHolder: CS_ClassViewHolder =
            holder as CS_ClassViewHolder
        rfManufactureYearHolder.bind(populateMasterListItem[position], clickListener)
    }

    override fun getItemCount(): Int = populateMasterListItem.size

    class CS_ClassViewHolder(
        private val binding: SpinnerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: PopulateStudentScholasticGradingItem, clickListener: OnItemClick) {
            binding.lblSpinnerItem.text = items.sStudentName
            binding.lblSpinnerItem.setOnClickListener {
                clickListener.onEditClick(items)
                CceScholasticInfoFragment.cboStudent!!.text = items.sStudentName
                CceScholasticInfoFragment.iStudentID = items.iStudentID
                CceScholasticInfoFragment.dialog!!.dismiss()
            }
        }
    }

    class OnItemClick(val clickListener: (items: PopulateStudentScholasticGradingItem) -> Unit) {
        fun onEditClick(items: PopulateStudentScholasticGradingItem) = clickListener(items)
    }
}