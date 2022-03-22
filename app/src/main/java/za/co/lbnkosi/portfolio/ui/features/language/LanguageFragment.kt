package za.co.lbnkosi.portfolio.ui.features.language

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import za.co.lbnkosi.portfolio.databinding.ChipLayoutBinding
import za.co.lbnkosi.portfolio.databinding.FragmentLanguagesBinding
import za.co.lbnkosi.portfolio.domain.model.Language
import za.co.lbnkosi.portfolio.domain.model.Skill
import za.co.lbnkosi.portfolio.ui.base.BaseFragment
import za.co.lbnkosi.portfolio.ui.features.home.HomeViewModel

class LanguageFragment : BaseFragment() {

    private lateinit var binding: FragmentLanguagesBinding

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLanguagesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.portfolio.observe(viewLifecycleOwner) {
            it?.languageList?.let { languages ->
                configureLanguagesChipGroup(languages)
            }
        }
    }

    private fun configureLanguagesChipGroup(languages: ArrayList<Language>) {
        languages.forEach {
            binding.chipGroup.addView(ChipLayoutBinding.inflate(layoutInflater).apply { this.chip.text = it.name }.root)
        }
    }

}