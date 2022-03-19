package za.co.lbnkosi.portfolio.ui.features.introslider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.databinding.IntroSliderThreeFragmentBinding
import za.co.lbnkosi.portfolio.ui.base.BaseFragment

@AndroidEntryPoint
class IntroSlider3 : BaseFragment() {

    private lateinit var mBinding: IntroSliderThreeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = IntroSliderThreeFragmentBinding.inflate(inflater, container, false)
        return mBinding.root
    }

}