package za.co.lbnkosi.portfolio.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.EducationBlockLayoutIncludeBinding
import za.co.lbnkosi.portfolio.domain.model.Education
import za.co.lbnkosi.portfolio.util.recyclerview.DataBoundListAdapter
import java.text.DateFormat
import java.text.SimpleDateFormat

class EducationBlockAdapter : DataBoundListAdapter<Education, EducationBlockLayoutIncludeBinding>() {

    override fun createBinding(parent: ViewGroup?): EducationBlockLayoutIncludeBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent!!.context), R.layout.education_block_layout_include, parent, false)
    }

    override fun bind(binding: EducationBlockLayoutIncludeBinding, item: Education) {
        Glide.with(binding.companyLogoImageView.context).load(item.logo).into(binding.companyLogoImageView)
        binding.companyNameTextview.text = item.name
        binding.positionTextView.text = item.alias
        binding.positionDurationTextView.text = formatPositionDate(item)
    }

    override fun areItemsTheSame(oldItem: Education, newItem: Education): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Education, newItem: Education): Boolean {
        return newItem == oldItem
    }

    private fun formatPositionDate(item: Education): String {
        val format: DateFormat = SimpleDateFormat("MMM yyyy")
        return format.format(item.startDate) + " - " + format.format(item.endDate)
    }
}