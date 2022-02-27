package za.co.lbnkosi.portfolio.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.FeaturedLayoutBinding
import za.co.lbnkosi.portfolio.databinding.ProjectsLayoutBinding
import za.co.lbnkosi.portfolio.domain.model.Project
import za.co.lbnkosi.portfolio.presentation.features.projects.ViewProjectCallback
import za.co.lbnkosi.portfolio.util.adapters.DataBoundListAdapter


class ProjectsAdapter(private val callback: ViewProjectCallback) : DataBoundListAdapter<Project, ProjectsLayoutBinding>() {

    override fun createBinding(parent: ViewGroup?): ProjectsLayoutBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent!!.context), R.layout.projects_layout, parent, false)
    }

    override fun bind(binding: ProjectsLayoutBinding, item: Project) {
        Glide.with(binding.projectImageView.context).load(item.image).error(R.drawable.android_logo).into(binding.projectImageView)
        binding.viewProjectButtonTextView.setOnClickListener { callback.onProjectClicked(item) }
        binding.projectDescriptionTextView.text = item.description
        binding.projectNameTextView.text = item.name
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean = newItem == oldItem

    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean = newItem == oldItem

}