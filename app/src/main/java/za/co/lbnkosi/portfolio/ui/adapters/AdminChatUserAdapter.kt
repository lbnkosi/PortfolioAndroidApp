package za.co.lbnkosi.portfolio.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.AdminChatUserLayoutBinding
import za.co.lbnkosi.portfolio.domain.model.ChatUser
import za.co.lbnkosi.portfolio.ui.features.chat.admin.ChatClickCallback
import za.co.lbnkosi.portfolio.util.recyclerview.DataBoundListAdapter

class AdminChatUserAdapter(private var callback: ChatClickCallback) : DataBoundListAdapter<ChatUser, AdminChatUserLayoutBinding>() {

    override fun createBinding(parent: ViewGroup?): AdminChatUserLayoutBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent!!.context), R.layout.admin_chat_user_layout, parent, false)
    }

    override fun bind(binding: AdminChatUserLayoutBinding, item: ChatUser) {
        binding.parent.setOnClickListener { callback.onChatClicked(item) }
        binding.userEmailTextView.text = item.email
        binding.userNameTextView.text = item.name
    }

    override fun areItemsTheSame(oldItem: ChatUser, newItem: ChatUser): Boolean {
        return newItem == oldItem
    }

    override fun areContentsTheSame(oldItem: ChatUser, newItem: ChatUser): Boolean {
        return newItem == oldItem
    }
}