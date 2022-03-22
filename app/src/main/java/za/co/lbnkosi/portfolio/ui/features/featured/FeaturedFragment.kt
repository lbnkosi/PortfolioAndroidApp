package za.co.lbnkosi.portfolio.ui.features.featured

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.databinding.FragmentFeaturedBinding
import za.co.lbnkosi.portfolio.domain.model.Project
import za.co.lbnkosi.portfolio.ui.base.BaseFragment
import za.co.lbnkosi.portfolio.ui.adapters.FeaturedAdapter
import za.co.lbnkosi.portfolio.ui.features.home.HomeViewModel
import za.co.lbnkosi.portfolio.ui.features.projects.ViewProjectCallback

@AndroidEntryPoint
class FeaturedFragment : BaseFragment(), ViewProjectCallback {

    private lateinit var binding: FragmentFeaturedBinding

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFeaturedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.portfolio.observe(viewLifecycleOwner) {
            it?.projectList?.let { projects ->
                configureFeaturedRecyclerView(projects.filter { predicate -> predicate.featured } as ArrayList<Project>)
            }
        }
    }

    private fun configureFeaturedRecyclerView(projects: ArrayList<Project>) {
        binding.featuredRecyclerView.adapter = FeaturedAdapter(this)
        (binding.featuredRecyclerView.adapter as FeaturedAdapter).replace(projects)
    }

    override fun onProjectClicked(project: Project) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(project.githubLink))
        startActivity(browserIntent)
    }

}