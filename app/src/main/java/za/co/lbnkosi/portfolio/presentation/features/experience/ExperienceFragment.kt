package za.co.lbnkosi.portfolio.presentation.features.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import za.co.lbnkosi.portfolio.databinding.FragmentWorkBinding
import za.co.lbnkosi.portfolio.domain.model.Work
import za.co.lbnkosi.portfolio.presentation.adapters.ExperienceAdapter
import za.co.lbnkosi.portfolio.presentation.base.BaseFragment
import za.co.lbnkosi.portfolio.presentation.features.home.HomeViewModel

class ExperienceFragment : BaseFragment() {

    private lateinit var binding: FragmentWorkBinding

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWorkBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.portfolio.observe(viewLifecycleOwner) {
            it?.workList?.let { experience ->
                configureExperienceRecyclerView(experience)
            }
        }
    }

    private fun configureExperienceRecyclerView(experience: ArrayList<Work>) {
        binding.workRecyclerView.adapter = ExperienceAdapter()
        (binding.workRecyclerView.adapter as ExperienceAdapter).replace(experience.reversed())
    }

}