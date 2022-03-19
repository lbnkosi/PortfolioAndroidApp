package za.co.lbnkosi.portfolio.ui.features.introslider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.databinding.IntroSliderOneFragmentBinding
import za.co.lbnkosi.portfolio.ui.base.BaseFragment

@AndroidEntryPoint
class IntroSlider1 : BaseFragment() {

    private lateinit var binding: IntroSliderOneFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        binding = IntroSliderOneFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}