package za.co.lbnkosi.portfolio.presentation.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import za.co.lbnkosi.portfolio.databinding.FragmentHomeBinding
import za.co.lbnkosi.portfolio.presentation.base.BaseFragment
import za.co.lbnkosi.portfolio.presentation.common.EducationBlockAdapter
import za.co.lbnkosi.portfolio.presentation.common.ExperienceBlockAdapter

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.portfolio.observe(viewLifecycleOwner) {
            binding.customAppbar.pbProcessing.isVisible = false
            binding.portfolio = it
        }

        viewModel.fetchPortfolio()

        binding.swipeToRefresh.setOnRefreshListener {
            binding.customAppbar.pbProcessing.isVisible = true
            viewModel.fetchPortfolio()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

}