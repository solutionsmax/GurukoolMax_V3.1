package com.solutionsmax.gurukoolmax_v3.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.solutionsmax.gurukoolmax_v3.core.data.main_menu.MainMenuItem
import com.solutionsmax.gurukoolmax_v3.databinding.MainMenuItemBinding

class MainMenuAdapter(
    private val listItems: List<MainMenuItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    class MainMenuViewHolder(
        private val binding: MainMenuItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: MainMenuItem, clickListener: OnItemClick) {
            binding.mainMenuItem = items
            Glide.with(binding.root).load(items.menuImage).into(binding.imgMenu)
            binding.clickListener = clickListener
        }
    }

    class OnItemClick(val clickListener: (menuName: String) -> Unit) {
        fun onClick(items: MainMenuItem) = clickListener(items.menuName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            MainMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mainHolder: MainMenuViewHolder = holder as MainMenuViewHolder
        mainHolder.bind(listItems[position], clickListener)
    }

    override fun getItemCount(): Int = listItems.size

}