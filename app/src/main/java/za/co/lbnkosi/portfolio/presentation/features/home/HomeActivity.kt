package za.co.lbnkosi.portfolio.presentation.features.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.ActivityHomeBinding
import za.co.lbnkosi.portfolio.domain.model.BackStack
import za.co.lbnkosi.portfolio.presentation.base.BaseActivity
import za.co.lbnkosi.portfolio.presentation.base.BaseFragment

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    private fun showHomeFragment() {
        binding.backStack = BackStack(isHome = true)
    }

    private fun showPortfolioFragment() {
        binding.backStack = BackStack(isPortfolio = true)
    }

    private fun showAboutFragment() {
        binding.backStack = BackStack(isAbout = true)
    }

    private fun showSettingsFragment() {
        binding.backStack = BackStack(isSettings = true)
    }

}