package za.co.lbnkosi.portfolio.presentation.features.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.ActivityHomeBinding
import za.co.lbnkosi.portfolio.presentation.base.BaseActivity

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        configureBottomNavigation()
    }

    private fun configureBottomNavigation() {
        binding.bottomNavigationView.selectedItemId = R.id.home
        binding.homeFragmentContainerView.isVisible = true
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    binding.chatFragmentContainerView.isVisible = false
                    binding.homeFragmentContainerView.isVisible = true
                    binding.settingsFragmentContainerView.isVisible = false
                    binding.aboutFragmentContainerView.isVisible = false
                    true
                }
                R.id.chat -> {
                    binding.chatFragmentContainerView.isVisible = true
                    binding.homeFragmentContainerView.isVisible = false
                    binding.settingsFragmentContainerView.isVisible = false
                    binding.aboutFragmentContainerView.isVisible = false
                    true
                }
                R.id.settings -> {
                    binding.settingsFragmentContainerView.isVisible = true
                    binding.chatFragmentContainerView.isVisible = false
                    binding.homeFragmentContainerView.isVisible = false
                    binding.aboutFragmentContainerView.isVisible = false
                    true
                }
                R.id.about -> {
                    binding.aboutFragmentContainerView.isVisible = true
                    binding.settingsFragmentContainerView.isVisible = false
                    binding.chatFragmentContainerView.isVisible = false
                    binding.homeFragmentContainerView.isVisible = false
                    true
                }
                else -> false
            }
        }
    }
}