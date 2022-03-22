package za.co.lbnkosi.portfolio.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.FeaturedLayoutBinding
import za.co.lbnkosi.portfolio.domain.model.Project
import za.co.lbnkosi.portfolio.ui.features.projects.ViewProjectCallback
import za.co.lbnkosi.portfolio.util.recyclerview.DataBoundListAdapter


class FeaturedAdapter(private val callback: ViewProjectCallback) : DataBoundListAdapter<Project, FeaturedLayoutBinding>() {

    override fun createBinding(parent: ViewGroup?): FeaturedLayoutBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent!!.context), R.layout.featured_layout, parent, false)
    }

    override fun bind(binding: FeaturedLayoutBinding, item: Project) {
        Glide.with(binding.root.context).load(item.image).into(binding.projectImageView)
        binding.projectImageView.setOnClickListener { callback.onProjectClicked(item) }
        binding.featuredCaptionTextView.text = item.description
        binding.featuredTitleTextView.text = item.name
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean = newItem == oldItem

    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean = newItem == oldItem

}