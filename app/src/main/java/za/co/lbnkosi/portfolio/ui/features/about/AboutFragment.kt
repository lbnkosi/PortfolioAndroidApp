package za.co.lbnkosi.portfolio.ui.features.about

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.databinding.FragmentAboutBinding
import za.co.lbnkosi.portfolio.domain.model.DynamicContent
import za.co.lbnkosi.portfolio.domain.model.Link
import za.co.lbnkosi.portfolio.ui.adapters.DynamicContentAdapter
import za.co.lbnkosi.portfolio.ui.base.BaseFragment
import za.co.lbnkosi.portfolio.ui.features.home.HomeViewModel


@AndroidEntryPoint
class AboutFragment : BaseFragment(), LinkCallback {

    private lateinit var binding: FragmentAboutBinding

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAboutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dynamicContent.observe(viewLifecycleOwner) { content ->
            content?.let {
                binding.dynamicContent = it
                configureDynamicContentRecyclerView(it)
            }
        }
        viewModel.fetchDynamicContent()
        setLottieFile()
    }

    private fun configureDynamicContentRecyclerView(dynamicContent: DynamicContent) {
        binding.dynamicContentRecyclerView.adapter = DynamicContentAdapter(this)
        (binding.dynamicContentRecyclerView.adapter as DynamicContentAdapter).replace(dynamicContent.linkList)
    }

    private fun setLottieFile() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.animation.setAnimation("programming_dark.json")
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.animation.setAnimation("programming_light.json")
            }
        }
    }

    override fun onLinkClick(link: Link) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link.link))
        startActivity(browserIntent)
    }

}