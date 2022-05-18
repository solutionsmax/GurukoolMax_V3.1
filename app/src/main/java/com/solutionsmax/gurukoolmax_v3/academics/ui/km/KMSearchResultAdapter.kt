package com.solutionsmax.gurukoolmax_v3.academics.ui.km

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.km.RetrieveKMInfo
import com.solutionsmax.gurukoolmax_v3.databinding.KmSearchResultItemBinding

class KMSearchResultAdapter(
    private val listItems: List<RetrieveKMInfo>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout =
            KmSearchResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KMSearchResultViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val kmSearchHolder: KMSearchResultViewHolder = holder as KMSearchResultViewHolder
        kmSearchHolder.bind(listItems[position])
    }

    override fun getItemCount(): Int = listItems.size

    class KMSearchResultViewHolder(
        private val binding: KmSearchResultItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RetrieveKMInfo) {
            binding.kmItem = item
        }
    }
}