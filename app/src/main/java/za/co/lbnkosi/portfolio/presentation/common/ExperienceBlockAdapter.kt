package za.co.lbnkosi.portfolio.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.ExperienceBlockLayoutIncludeBinding
import za.co.lbnkosi.portfolio.domain.model.Work

class ExperienceBlockAdapter : DataBoundListAdapter<Work, ExperienceBlockLayoutIncludeBinding>() {

    override fun createBinding(parent: ViewGroup?): ExperienceBlockLayoutIncludeBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent!!.context), R.layout.experience_block_layout_include, parent, false)
    }

    override fun bind(binding: ExperienceBlockLayoutIncludeBinding, item: Work) {
        Glide.with(binding.expImageView.context).load(item.logo).into(binding.expImageView)
        binding.companyTextView.text = item.companyName
        binding.positionTextView.text = item.position
    }

    override fun areItemsTheSame(oldItem: Work, newItem: Work): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Work, newItem: Work): Boolean {
        return newItem.companyName == oldItem.companyName
    }
}