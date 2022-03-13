package za.co.lbnkosi.portfolio.ui.features.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import za.co.lbnkosi.portfolio.databinding.FragmentSettingsBinding
import za.co.lbnkosi.portfolio.ui.base.BaseFragment
import za.co.lbnkosi.portfolio.ui.features.chat.user.ChatViewModel
import za.co.lbnkosi.portfolio.util.dialogs.DialogUtil

class SettingsFragment : BaseFragment() {

    private lateinit var binding: FragmentSettingsBinding

    private val viewModel: ChatViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logoutButton.setOnClickListener {
            DialogUtil.showDialog(requireContext(), null, "Are you sure you want to logout", "Yes", { _, _ -> logout() }, "Cancel", { _, _ -> })
        }
        binding.deleteAccountButton.setOnClickListener {
            DialogUtil.showDialog(requireContext(), null, "Are you sure you want to delete your account", "Yes", { _, _ -> deleteAccount() }, "Cancel", { _, _ -> })
        }
        viewModel.isSignedIn().isSigned(binding)
    }

    private fun logout() {
        viewModel.signOut()
        false.isSigned(binding)
        Snackbar.make(binding.root, "You have been logged out", Snackbar.LENGTH_SHORT).show()
    }

    private fun deleteAccount() {
        viewModel.deleteAccount()
        false.isSigned(binding)
    }

}