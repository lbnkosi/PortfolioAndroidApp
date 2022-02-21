package za.co.lbnkosi.portfolio.presentation.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.FragmentHomeBinding
import za.co.lbnkosi.portfolio.domain.model.Portfolio
import za.co.lbnkosi.portfolio.domain.model.User
import za.co.lbnkosi.portfolio.presentation.base.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var seeMore: Boolean = false

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.portfolio.observe(viewLifecycleOwner) {
            //binding.loadingIndicator.root.isVisible = false
            binding.headerInclude.portfolio = it
            it?.user?.let { user ->
                setupImages(user)
            }
            it?.let { portfolio ->
                setupHeaderTextViews(portfolio)
            }
        }

        viewModel.fetchPortfolio()
        //binding.loadingIndicator.root.isVisible = true

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.fetchPortfolio()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun setupHeaderTextViews(portfolio: Portfolio) {
        portfolio.workList.firstOrNull { predicate -> predicate.currentPosition }.apply {
            binding.headerInclude.titleTextView.text = this?.position + " at " + this?.companyName
        }

        val currentWork = portfolio.workList.firstOrNull { predicate -> predicate.currentPosition }
        val currentSchool = portfolio.educationList.firstOrNull { predicate -> predicate.enrolled }

        binding.headerInclude.workAndEducationTextView.text = currentWork?.companyName + " - " + currentSchool?.name

        portfolio.addressList.firstOrNull { predicate -> predicate.addressType == "WORK" }.apply {
            binding.headerInclude.cityTextView.text = this?.city + ", " + this?.province
        }

        binding.headerInclude.aboutTextView.maxLines = 3
        binding.headerInclude.aboutTextView.text = portfolio.user.summary

        binding.headerInclude.seeMoreTextView.setOnClickListener {
            if (!seeMore) {
                seeMore = true
                binding.headerInclude.aboutTextView.maxLines = 1000
                binding.headerInclude.seeMoreTextView.text = getString(R.string.see_less_button)
            } else {
                seeMore = false
                binding.headerInclude.aboutTextView.maxLines = 3
                binding.headerInclude.seeMoreTextView.text = getString(R.string.see_more_button)
            }
        }

    }

    private fun setupImages(user: User) {
        user.headerImage.let {
            Glide.with(this).load(it).placeholder(R.drawable.header_placeholder).error(R.drawable.header_image).into(binding.headerInclude.headerImageView)
        }

        user.profileImage.let {
            Glide.with(this).load(it).placeholder(R.drawable.profile_placeholder).error(R.drawable.subject).into(binding.headerInclude.profileImageView)
        }
    }

}