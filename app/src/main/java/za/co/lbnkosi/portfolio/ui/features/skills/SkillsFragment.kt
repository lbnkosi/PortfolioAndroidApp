package za.co.lbnkosi.portfolio.ui.features.skills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import za.co.lbnkosi.portfolio.databinding.ChipLayoutBinding
import za.co.lbnkosi.portfolio.databinding.FragmentSkillsBinding
import za.co.lbnkosi.portfolio.domain.model.Skill
import za.co.lbnkosi.portfolio.ui.base.BaseFragment
import za.co.lbnkosi.portfolio.ui.features.home.HomeViewModel

@AndroidEntryPoint
class SkillsFragment : BaseFragment() {

    private lateinit var binding: FragmentSkillsBinding

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSkillsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.portfolio.observe(viewLifecycleOwner) {
            it?.skillsList?.let { skills ->
                configureSkillsChipGroup(skills)
            }
        }
    }

    private fun configureSkillsChipGroup(skills: ArrayList<Skill>) {
        skills.forEach {
            binding.chipGroup.addView(ChipLayoutBinding.inflate(layoutInflater).apply { this.chip.text = it.name }.root)
        }
    }
}