package za.co.lbnkosi.portfolio.ui.features.chat.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.databinding.FragmentChatBinding
import za.co.lbnkosi.portfolio.ui.base.BaseFragment
import za.co.lbnkosi.portfolio.data.network.Constants.FIRE_UID
import za.co.lbnkosi.portfolio.ui.features.chat.admin.AdminChatActivity

@AndroidEntryPoint
class ChatFragment : BaseFragment(), SignedInCallback {

    private lateinit var binding: FragmentChatBinding

    private val viewModel: ChatViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openAdminSpaceIfLoggedIn()
        observeAuthentication()
        configureAuthScreen(viewModel.isSignedIn())
    }

    private fun observeAuthentication() {
        viewModel.signedIn.observe(viewLifecycleOwner) {
            configureAuthScreen(it)
            dismissLoadingDialog()
        }
    }

    private fun configureAuthScreen(signedIn: Boolean) {
        binding.chatFragmentContainerView.isVisible = signedIn
        binding.authFragmentContainerView.isVisible = !signedIn
    }

    private fun openAdminSpaceIfLoggedIn() {
        viewModel.getSignedInUser()?.let {
            if (it.uid == FIRE_UID) {
                binding.adminSpaceButtonTextView.isVisible = true
                binding.adminSpaceButtonTextView.setOnClickListener {
                    startActivity(Intent(requireActivity(), AdminChatActivity::class.java))
                }
            }
        }
    }

    override fun signedIn() {

    }

}