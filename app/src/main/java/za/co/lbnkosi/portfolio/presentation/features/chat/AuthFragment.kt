package za.co.lbnkosi.portfolio.presentation.features.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.databinding.FragmentAuthBinding
import za.co.lbnkosi.portfolio.presentation.base.BaseFragment

@AndroidEntryPoint
class AuthFragment : BaseFragment() {

    private var shouldSignIn: Boolean = true

    private lateinit var binding: FragmentAuthBinding

    private val viewModel: ChatViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAuthBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        configureOnClicks()
        binding.authTextView.setOnClickListener {
            binding.signUpGroup.isVisible = !binding.signUpGroup.isVisible
            binding.authButton.text = if (binding.signUpGroup.isVisible) "Sign Up" else "Sign In"
            binding.authTextView.text = if (binding.signUpGroup.isVisible) "Sign In" else "Sign Up"
            shouldSignIn = !binding.signUpGroup.isVisible
        }
    }

    private fun configureOnClicks() {
        binding.authButton.setOnClickListener {
            if (shouldSignIn) {
                viewModel.signIn()
            } else {
                viewModel.signUp()
            }
        }
    }
}