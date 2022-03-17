package com.solutionsmax.gurukoolmax_v3.operations.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.solutionsmax.gurukoolmax_v3.databinding.OperationsMenuItemBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationsMenuItem

class OperationsMenuAdapter(
    private val listItems: List<OperationsMenuItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            OperationsMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OperationMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val operationsHolder: OperationMenuViewHolder = holder as OperationMenuViewHolder
        operationsHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

    class OperationMenuViewHolder(
        val binding: OperationsMenuItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: OperationsMenuItem, clickListener: OnItemClick) {
            binding.operationMenuItem = items
            Glide.with(binding.root).load(items.opMenuImage).into(binding.imgMenu)
            binding.clickListener = clickListener
        }
    }

    class OnItemClick(val clickListener: (menuName: String) -> Unit) {
        fun onClick(items: OperationsMenuItem) = clickListener(items.opMenuName)
    }
}