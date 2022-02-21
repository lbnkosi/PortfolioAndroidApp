package za.co.lbnkosi.portfolio.presentation.features.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.FragmentChatBinding
import za.co.lbnkosi.portfolio.presentation.base.BaseActivity
import za.co.lbnkosi.portfolio.presentation.base.BaseFragment
import za.co.lbnkosi.portfolio.presentation.features.home.HomeActivity
import za.co.lbnkosi.portfolio.presentation.features.home.HomeViewModel

@AndroidEntryPoint
class ChatFragment : BaseFragment(), SignedInCallback {

    private lateinit var binding: FragmentChatBinding

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAuthentication()
        configureAuthScreen(viewModel.isSignedIn())
    }

    private fun observeAuthentication() {
        viewModel.signedIn.observe(viewLifecycleOwner) {
            configureAuthScreen(it)
        }
    }

    private fun configureAuthScreen(signedIn: Boolean) {
        binding.chatFragmentContainerView.isVisible = signedIn
        binding.authFragmentContainerView.isVisible = !signedIn
    }

    override fun signedIn() {

    }

}