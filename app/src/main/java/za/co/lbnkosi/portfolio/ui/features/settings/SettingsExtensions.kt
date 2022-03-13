package za.co.lbnkosi.portfolio.ui.features.settings

import androidx.core.view.isVisible
import za.co.lbnkosi.portfolio.databinding.FragmentSettingsBinding

fun Boolean.isSigned(binding: FragmentSettingsBinding) {
    binding.logoutGroup.isVisible = this
    binding.deleteGroup.isVisible = this
    binding.settingsCaption.isVisible = !this
}