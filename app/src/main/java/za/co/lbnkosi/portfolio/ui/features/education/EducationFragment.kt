package za.co.lbnkosi.portfolio.ui.features.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import za.co.lbnkosi.portfolio.databinding.FragmentEducationBinding
import za.co.lbnkosi.portfolio.domain.model.Education
import za.co.lbnkosi.portfolio.ui.adapters.EducationBlockAdapter
import za.co.lbnkosi.portfolio.ui.base.BaseFragment
import za.co.lbnkosi.portfolio.ui.features.home.HomeViewModel

class EducationFragment : BaseFragment() {

    private lateinit var binding: FragmentEducationBinding

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEducationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeButton.caption.text = "See More"
        viewModel.portfolio.observe(viewLifecycleOwner) {
            it?.educationList?.let { education ->
                configureEducationRecyclerView(education)
            }
        }
    }

    private fun configureEducationRecyclerView(experience: ArrayList<Education>) {
        binding.educationRecyclerView.adapter = EducationBlockAdapter()
        (binding.educationRecyclerView.adapter as EducationBlockAdapter).replace(experience.reversed())
    }

}