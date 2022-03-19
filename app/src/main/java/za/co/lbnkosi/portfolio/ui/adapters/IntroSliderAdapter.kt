package za.co.lbnkosi.portfolio.ui.adapters

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import za.co.lbnkosi.portfolio.ui.features.introslider.IntroSlider1
import za.co.lbnkosi.portfolio.ui.features.introslider.IntroSlider2
import za.co.lbnkosi.portfolio.ui.features.introslider.IntroSlider3

class IntroSliderAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    companion object {
        const val NUM_PAGES = 3
    }

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int) = when (position) {
        0 -> IntroSlider1()
        1 -> IntroSlider2()
        else -> IntroSlider3()
    }
}