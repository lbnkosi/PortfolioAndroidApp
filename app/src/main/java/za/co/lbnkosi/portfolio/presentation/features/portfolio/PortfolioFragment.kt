package za.co.lbnkosi.portfolio.presentation.features.portfolio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import za.co.lbnkosi.portfolio.databinding.FragmentPortfolioBinding
import za.co.lbnkosi.portfolio.presentation.base.BaseFragment
import za.co.lbnkosi.portfolio.presentation.features.home.HomeViewModel

class PortfolioFragment : BaseFragment() {

    private lateinit var binding: FragmentPortfolioBinding

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPortfolioBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}