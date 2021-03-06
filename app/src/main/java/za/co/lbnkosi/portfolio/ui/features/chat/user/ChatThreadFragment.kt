package za.co.lbnkosi.portfolio.ui.features.chat.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.databinding.FragmentChatThreadBinding
import za.co.lbnkosi.portfolio.ui.adapters.ChatAdapter
import za.co.lbnkosi.portfolio.ui.base.BaseFragment

@AndroidEntryPoint
class ChatThreadFragment : BaseFragment() {

    private lateinit var binding: FragmentChatThreadBinding

    private val viewModel: ChatViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChatThreadBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.chatRecyclerView.adapter = ChatAdapter()
        configureObservables()
        viewModel.fetchMessages()
        binding.sendImageView.setOnClickListener {
            viewModel.sendMessage()
        }
        fetchAvatar()
    }

    private fun fetchAvatar() {
        viewModel.portfolio.observe(viewLifecycleOwner) {
            it?.let {
                Glide.with(requireContext()).load(it.user.profileImage).into(binding.profileImageView)
            }
        }
        viewModel.fetchPortfolioFromCache()
    }

    private fun configureObservables() {
        viewModel.messages.observe(viewLifecycleOwner) {
            (binding.chatRecyclerView.adapter as ChatAdapter).replace(it.reversed())
        }
    }
}