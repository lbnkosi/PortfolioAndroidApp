package za.co.lbnkosi.portfolio.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.ChatBubbleLayoutBinding
import za.co.lbnkosi.portfolio.domain.model.ChatModel
import za.co.lbnkosi.portfolio.presentation.common.Constants.FIRE_UID
import za.co.lbnkosi.portfolio.util.adapters.DataBoundListAdapter

class ChatAdapter : DataBoundListAdapter<ChatModel, ChatBubbleLayoutBinding>() {

    override fun createBinding(parent: ViewGroup?): ChatBubbleLayoutBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent!!.context), R.layout.chat_bubble_layout, parent, false)
    }

    override fun bind(binding: ChatBubbleLayoutBinding, item: ChatModel) {
        if (item.uid != FIRE_UID) {
            binding.rightBubbleTextView.isVisible = true
            binding.rightBubbleTextView.text = item.message
        } else {
            binding.leftBubbleTextView.isVisible = true
            binding.leftBubbleTextView.text = item.message
        }
    }

    override fun areItemsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
        return newItem == oldItem
    }

    override fun areContentsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
        return newItem == oldItem
    }
}