package com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.project.RetrieveAcademicProjectItem
import com.solutionsmax.gurukoolmax_v3.databinding.ProjectRepositorySearchAdapterBinding

class ProjectRepositorySearchAdapter(private val listItems: List<RetrieveAcademicProjectItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = ProjectRepositorySearchAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProjectRepositorySearchViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val projectRepoHolder: ProjectRepositorySearchViewHolder =
            holder as ProjectRepositorySearchViewHolder
        projectRepoHolder.bind(listItems[position])
    }

    override fun getItemCount(): Int = listItems.size

    class ProjectRepositorySearchViewHolder(
        private val projectRepositorySearch: ProjectRepositorySearchAdapterBinding
    ) : RecyclerView.ViewHolder(projectRepositorySearch.root) {
        fun bind(items: RetrieveAcademicProjectItem) {
            projectRepositorySearch.projectItems = items
        }
    }

}