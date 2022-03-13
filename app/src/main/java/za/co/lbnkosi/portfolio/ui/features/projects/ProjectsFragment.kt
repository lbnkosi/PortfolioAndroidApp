package za.co.lbnkosi.portfolio.ui.features.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import za.co.lbnkosi.portfolio.databinding.FragmentProjectsBinding
import za.co.lbnkosi.portfolio.domain.model.Project
import za.co.lbnkosi.portfolio.ui.adapters.ProjectsAdapter
import za.co.lbnkosi.portfolio.ui.base.BaseFragment
import za.co.lbnkosi.portfolio.ui.features.home.HomeViewModel

class ProjectsFragment : BaseFragment(), ViewProjectCallback {

    private lateinit var binding: FragmentProjectsBinding

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProjectsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.portfolio.observe(viewLifecycleOwner) {
            it?.projectList?.let { projects ->
                configureProjectsRecyclerView(projects)
            }
        }
    }

    private fun configureProjectsRecyclerView(projects: ArrayList<Project>) {
        binding.projectsRecyclerView.adapter = ProjectsAdapter(this)
        (binding.projectsRecyclerView.adapter as ProjectsAdapter).replace(projects)
    }

    override fun onProjectClicked(project: Project) {
        //open webview or something
    }

}