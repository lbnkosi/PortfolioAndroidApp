package za.co.lbnkosi.portfolio.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.DynamicContentListLayoutBinding
import za.co.lbnkosi.portfolio.domain.model.Link
import za.co.lbnkosi.portfolio.ui.features.about.LinkCallback
import za.co.lbnkosi.portfolio.util.recyclerview.DataBoundListAdapter

class DynamicContentAdapter(private val linkCallback: LinkCallback) : DataBoundListAdapter<Link, DynamicContentListLayoutBinding>() {

    override fun createBinding(parent: ViewGroup?): DynamicContentListLayoutBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent!!.context), R.layout.dynamic_content_list_layout, parent, false)
    }

    override fun bind(binding: DynamicContentListLayoutBinding, item: Link) {
        binding.aliasTextView.text = item.alias
        binding.root.setOnClickListener { linkCallback.onLinkClick(item) }
    }

    override fun areContentsTheSame(oldItem: Link, newItem: Link): Boolean = newItem == oldItem

    override fun areItemsTheSame(oldItem: Link, newItem: Link): Boolean = newItem == oldItem

}