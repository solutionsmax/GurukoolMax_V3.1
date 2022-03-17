package com.solutionsmax.gurukoolmax_v3.core.ui.custom

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.databinding.SpinnerItemBinding

class SpinnerItemAdapter(private val listItems: List<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = listItems.size

    class SpinnerItemViewHolder(private val binding: SpinnerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(items: Any) {
            binding.lblSpinnerItem.text = items.toString()
        }
    }
}