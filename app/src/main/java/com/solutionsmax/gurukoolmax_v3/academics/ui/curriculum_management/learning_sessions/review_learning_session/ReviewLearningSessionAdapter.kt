package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions.review_learning_session

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.learning_session.RetrieveLearningSessionItem
import com.solutionsmax.gurukoolmax_v3.databinding.ReviewLearningSessionItemBinding

class ReviewLearningSessionAdapter(
    private val listItems: List<RetrieveLearningSessionItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = ReviewLearningSessionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReviewLearningSessionViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reviewSessionHolder: ReviewLearningSessionViewHolder =
            holder as ReviewLearningSessionViewHolder
        reviewSessionHolder.bind(listItems[position])
    }

    override fun getItemCount(): Int = listItems.size

    class ReviewLearningSessionViewHolder(
        private val binding: ReviewLearningSessionItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: RetrieveLearningSessionItem) {
            binding.learningSession = items
        }
    }
}