package za.co.lbnkosi.portfolio.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.ExperienceBlockLayoutIncludeBinding
import za.co.lbnkosi.portfolio.domain.model.Work
import za.co.lbnkosi.portfolio.util.adapters.DataBoundListAdapter
import java.text.DateFormat
import java.text.SimpleDateFormat

class ExperienceAdapter : DataBoundListAdapter<Work, ExperienceBlockLayoutIncludeBinding>() {

    private var seeMore: Boolean = false

    override fun createBinding(parent: ViewGroup?): ExperienceBlockLayoutIncludeBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent!!.context), R.layout.experience_block_layout_include, parent, false)
    }

    override fun bind(binding: ExperienceBlockLayoutIncludeBinding, item: Work) {
        Glide.with(binding.companyLogoImageView.context).load(item.logo).into(binding.companyLogoImageView)
        binding.companyNameTextview.text = item.companyName
        binding.positionTextView.text = item.position
        binding.positionDurationTextView.text = formatPositionDate(item)
        configureDescriptionText(binding, item)
    }

    override fun areItemsTheSame(oldItem: Work, newItem: Work): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Work, newItem: Work): Boolean {
        return newItem.companyName == oldItem.companyName
    }

    private fun configureDescriptionText(binding: ExperienceBlockLayoutIncludeBinding, item: Work) {
        binding.positionDescription.maxLines = 3
        binding.positionDescription.text = item.description
        binding.seeMoreTextView.setOnClickListener {
            if (!seeMore) {
                seeMore = true
                binding.positionDescription.maxLines = 1000
                binding.seeMoreTextView.text = binding.root.context.getString(R.string.see_less_button)
            } else {
                seeMore = false
                binding.positionDescription.maxLines = 3
                binding.seeMoreTextView.text = binding.root.context.getString(R.string.see_more_button)
            }
        }
    }

    private fun formatPositionDate(item: Work): String {
        val format: DateFormat = SimpleDateFormat("MMM yyyy")
        return format.format(item.startDate) + " - " + format.format(item.endDate)
    }
}