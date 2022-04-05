package za.co.lbnkosi.portfolio.ui.features.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import za.co.lbnkosi.portfolio.databinding.FragmentWorkBinding
import za.co.lbnkosi.portfolio.domain.enums.FragmentState
import za.co.lbnkosi.portfolio.domain.enums.FragmentState.COLLAPSED
import za.co.lbnkosi.portfolio.domain.model.Work
import za.co.lbnkosi.portfolio.ui.adapters.ExperienceAdapter
import za.co.lbnkosi.portfolio.ui.base.BaseFragment
import za.co.lbnkosi.portfolio.ui.features.home.HomeViewModel
import kotlin.math.exp

class ExperienceFragment : BaseFragment() {

    private var state: FragmentState = COLLAPSED

    private lateinit var binding: FragmentWorkBinding

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWorkBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeButton.caption.text = "See More"
        viewModel.portfolio.observe(viewLifecycleOwner) {
            it?.workList?.let { experience ->
                configureExperienceRecyclerView(experience)
            }
        }
    }

    private fun configureExperienceRecyclerView(experience: ArrayList<Work>) {
        binding.workRecyclerView.adapter = ExperienceAdapter()
        (binding.workRecyclerView.adapter as ExperienceAdapter).replace(getExperienceList(experience).reversed())
    }

    private fun getExperienceList(experience: ArrayList<Work>): ArrayList<Work> {
        val experienceList: ArrayList<Work> = arrayListOf()
        if (!experience.isNullOrEmpty()) {
            if (state == COLLAPSED) {
                experienceList.add(experience.reversed().first())
            } else {
                return experience
            }
            return experienceList
        } else {
            return experience
        }
    }

}