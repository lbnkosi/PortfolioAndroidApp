package za.co.lbnkosi.portfolio.ui.features.chat.admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.ActivityAdminChatBinding
import za.co.lbnkosi.portfolio.domain.model.ChatUser
import za.co.lbnkosi.portfolio.ui.adapters.AdminChatUserAdapter
import za.co.lbnkosi.portfolio.ui.base.BaseActivity
import za.co.lbnkosi.portfolio.ui.features.chat.user.ChatViewModel
import za.co.lbnkosi.portfolio.ui.features.home.HomeViewModel

@AndroidEntryPoint
class AdminChatActivity : BaseActivity(), ChatClickCallback {

    private val homeViewModel: HomeViewModel by viewModels()

    private val chatViewModel: ChatViewModel by viewModels()

    private lateinit var binding: ActivityAdminChatBinding

    companion object {
        const val SELECTED_UID = "selected_uid"
        const val SELECTED_EMAIL = "selected_email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_chat)
        chatViewModel.chatUsers.observe(this) { chatList ->
            chatList?.let {
                configureChatListRecyclerView(it)
            }
        }
        chatViewModel.fetchChatUsers()
    }

    private fun configureChatListRecyclerView(chatList: ArrayList<ChatUser>) {
        binding.chatListRecyclerView.adapter = AdminChatUserAdapter(this)
        (binding.chatListRecyclerView.adapter as AdminChatUserAdapter).replace(chatList)
    }

    override fun onChatClicked(user: ChatUser) {
        val intent = Intent(this, AdminChatThreadActivity::class.java)
        intent.putExtra(SELECTED_UID, user.uid)
        intent.putExtra(SELECTED_EMAIL, user.email)
        startActivity(intent)
    }

}