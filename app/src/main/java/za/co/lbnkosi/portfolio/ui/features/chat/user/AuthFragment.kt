package za.co.lbnkosi.portfolio.ui.features.chat.user

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.FragmentAuthBinding
import za.co.lbnkosi.portfolio.ui.base.BaseFragment

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
            binding.authButton.text = if (binding.signUpGroup.isVisible) getString(R.string.sign_up) else getString(R.string.sign_in)
            binding.authTextView.text = if (binding.signUpGroup.isVisible) getString(R.string.sign_in) else getString(R.string.sign_up)
            shouldSignIn = !binding.signUpGroup.isVisible
        }
        setLottieFile()
    }

    private fun configureOnClicks() {
        binding.authButton.setOnClickListener {
            showLoadingDialog("Logging you in...")
            if (shouldSignIn) {
                viewModel.signIn()
            } else {
                viewModel.signUp()
            }
        }
    }

    private fun setLottieFile() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.animation.setAnimation("login_black.json")
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.animation.setAnimation("login_white.json")
            }
        }
    }
}