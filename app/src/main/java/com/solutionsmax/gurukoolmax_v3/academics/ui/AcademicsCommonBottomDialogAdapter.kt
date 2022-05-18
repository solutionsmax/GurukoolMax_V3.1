package com.solutionsmax.gurukoolmax_v3.academics.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.data.AcademicsBottomSheetItem
import com.solutionsmax.gurukoolmax_v3.databinding.BottomSheetDialogListItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationsMenuItem

class AcademicsCommonBottomDialogAdapter(
    private val listItems: List<AcademicsBottomSheetItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = BottomSheetDialogListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AcademicsCommonBottomDialogViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val academicsCommonBottomDialogViewHolder: AcademicsCommonBottomDialogViewHolder =
            holder as AcademicsCommonBottomDialogViewHolder
        academicsCommonBottomDialogViewHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class AcademicsCommonBottomDialogViewHolder(
        private val binding: BottomSheetDialogListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AcademicsBottomSheetItem, clickListener: OnItemClick) {
            binding.items = item
            binding.clickListener = clickListener
        }
    }

    class OnItemClick(val clickListener: (menuName: String) -> Unit) {
        fun onClick(items: AcademicsBottomSheetItem) = clickListener(items.itemName)
    }
}