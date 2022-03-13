package za.co.lbnkosi.portfolio.ui.features.chat.admin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.FragmentChatThreadBinding
import za.co.lbnkosi.portfolio.ui.adapters.ChatAdapter
import za.co.lbnkosi.portfolio.ui.base.BaseActivity
import za.co.lbnkosi.portfolio.ui.features.chat.user.ChatViewModel
import za.co.lbnkosi.portfolio.ui.features.home.HomeViewModel

@AndroidEntryPoint
class AdminChatThreadActivity : BaseActivity() {

    private var uid: String? = null

    private var email: String? = null

    private val homeViewModel: HomeViewModel by viewModels()

    private val chatViewModel: ChatViewModel by viewModels()

    //Uses the same layout as the user
    private lateinit var binding: FragmentChatThreadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchExtras()
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_chat_thread)
        binding.viewModel = chatViewModel
        binding.chatRecyclerView.adapter = ChatAdapter()
        configureObservables()
        uid?.let { aUID ->
            chatViewModel.fetchMessages(aUID)
            chatViewModel.messagesSnapShotListener(aUID)
        }
        binding.sendImageView.setOnClickListener {
            uid?.let { aUID ->
                chatViewModel.sendMessage(aUID, email?.substringBefore("@"))
            }
        }
    }

    private fun fetchExtras() {
        uid = intent.getStringExtra(AdminChatActivity.SELECTED_UID)
        email = intent.getStringExtra(AdminChatActivity.SELECTED_EMAIL)
    }

    private fun configureObservables() {
        chatViewModel.messages.observe(this) {
            (binding.chatRecyclerView.adapter as ChatAdapter).replace(it.reversed())
        }
    }
}