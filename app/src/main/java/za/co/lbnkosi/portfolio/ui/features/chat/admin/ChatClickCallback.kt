package za.co.lbnkosi.portfolio.ui.features.chat.admin

import za.co.lbnkosi.portfolio.domain.model.ChatUser

interface ChatClickCallback {
    fun onChatClicked(user: ChatUser)
}