package za.co.lbnkosi.portfolio.ui.features.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.R
import za.co.lbnkosi.portfolio.databinding.FragmentHomeBinding
import za.co.lbnkosi.portfolio.domain.enums.ScreenState
import za.co.lbnkosi.portfolio.domain.model.Portfolio
import za.co.lbnkosi.portfolio.ui.base.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var seeMore: Boolean = false

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by activityViewModels()

    companion object {
        const val MAX_LINES_MIN = 3
        const val MAX_LINES_MAX = 1000
        const val ADDRESS_TYPE_WORK = "WORK"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingIndicator.viewModel = viewModel
        configureSwipeToRefresh()
        configureObservables()
    }

    private fun configureObservables() {
        viewModel.portfolio.observe(viewLifecycleOwner) {
            binding.headerInclude.portfolio = it
            it?.let { portfolio ->
                setupHeaderTextViews(portfolio)
                configureSeeMoreTextView(portfolio)
                openResume(portfolio.user.resume)
            }
        }
        viewModel.fetchPortfolio()
    }

    private fun configureSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.fetchPortfolio()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun setupHeaderTextViews(portfolio: Portfolio) {
        portfolio.workList.firstOrNull { predicate -> predicate.currentPosition }.apply { binding.headerInclude.titleTextView.text = getString(R.string.position_company_name, this?.position, this?.companyName) }
        val currentWork = portfolio.workList.firstOrNull { predicate -> predicate.currentPosition }
        val currentSchool = portfolio.educationList.firstOrNull { predicate -> predicate.enrolled }
        binding.headerInclude.workAndEducationTextView.text = getString(R.string.company_name_school_name, currentWork?.companyName, currentSchool?.name)
        portfolio.addressList.firstOrNull { it.addressType == ADDRESS_TYPE_WORK }.apply { binding.headerInclude.cityTextView.text = getString(R.string.city_province, this?.city, this?.province) }
    }

    private fun configureSeeMoreTextView(portfolio: Portfolio) {
        binding.headerInclude.apply {
            aboutTextView.text = portfolio.user.summary
            aboutTextView.maxLines = MAX_LINES_MIN
            seeMoreTextView.setOnClickListener {
                seeMoreTextView.text = if (!seeMore) getString(R.string.see_less_button) else getString(R.string.see_more_button)
                aboutTextView.maxLines = if (!seeMore) MAX_LINES_MAX else MAX_LINES_MIN
                seeMore = !seeMore
            }
        }
    }

    private fun openResume(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        binding.headerInclude.downloadResumeTextView.setOnClickListener {
            startActivity(browserIntent)
        }
    }

}