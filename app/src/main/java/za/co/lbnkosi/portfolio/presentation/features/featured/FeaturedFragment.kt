package za.co.lbnkosi.portfolio.presentation.features.featured

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.databinding.FragmentFeaturedBinding
import za.co.lbnkosi.portfolio.domain.model.Project
import za.co.lbnkosi.portfolio.presentation.base.BaseFragment
import za.co.lbnkosi.portfolio.presentation.adapters.FeaturedAdapter
import za.co.lbnkosi.portfolio.presentation.features.home.HomeViewModel

@AndroidEntryPoint
class FeaturedFragment : BaseFragment() {

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
        binding.featuredRecyclerView.adapter = FeaturedAdapter()
        (binding.featuredRecyclerView.adapter as FeaturedAdapter).replace(projects)
    }

}