package za.co.lbnkosi.portfolio.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.SkillsLayoutBinding
import za.co.lbnkosi.portfolio.domain.model.Skill
import za.co.lbnkosi.portfolio.util.adapters.DataBoundListAdapter

class SkillsAdapter : DataBoundListAdapter<Skill, SkillsLayoutBinding>() {

    override fun createBinding(parent: ViewGroup?): SkillsLayoutBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent!!.context), R.layout.skills_layout, parent, false)
    }

    override fun bind(binding: SkillsLayoutBinding, item: Skill) {
        binding.skillTextView.text = item.name
    }

    override fun areContentsTheSame(oldItem: Skill, newItem: Skill): Boolean = newItem == oldItem

    override fun areItemsTheSame(oldItem: Skill, newItem: Skill): Boolean = newItem == oldItem

}