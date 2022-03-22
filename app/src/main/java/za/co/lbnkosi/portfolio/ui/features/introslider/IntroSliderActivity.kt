package za.co.lbnkosi.portfolio.ui.features.introslider

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.ActivityIntroSliderBinding
import za.co.lbnkosi.portfolio.ui.adapters.IntroSliderAdapter
import za.co.lbnkosi.portfolio.ui.base.BaseActivity
import za.co.lbnkosi.portfolio.ui.features.home.HomeActivity

@AndroidEntryPoint
class IntroSliderActivity : BaseActivity() {

    private lateinit var binding: ActivityIntroSliderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro_slider)
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewPager.adapter = IntroSliderAdapter(this)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) = Unit
            override fun onPageSelected(position: Int) = configureNavigation(position)
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit
        })
    }

    private fun configureNavigation(position: Int) {
        binding.navigation.backButton.visibility = if (position != 0) View.VISIBLE else View.INVISIBLE
        configureIntroSliderDots(position)
        when (position) {
            0 -> configureIntroSliderNavigation(1, 0)
            1 -> configureIntroSliderNavigation(2, 0)
            else -> configureIntroSliderNavigation(3, 1)
        }
    }

    private fun configureIntroSliderDots(position: Int) {
        binding.navigation.slideOne.isSelected = position == 0
        binding.navigation.slideTwo.isSelected = position == 1
        binding.navigation.slideThree.isSelected = position == 2
    }

    private fun configureIntroSliderNavigation(nextPosition: Int, backPosition: Int) {
        binding.apply {
            navigation.backButton.setOnClickListener { viewPager.currentItem = backPosition }
            navigation.nextButton.setOnClickListener { viewPager.currentItem = nextPosition }
            if (nextPosition == 3) navigation.nextButton.setOnClickListener {
                startActivity(Intent(this@IntroSliderActivity, HomeActivity::class.java))
            }
        }
    }

}