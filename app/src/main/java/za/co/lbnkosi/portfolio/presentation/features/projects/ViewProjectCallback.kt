package za.co.lbnkosi.portfolio.presentation.features.projects

import za.co.lbnkosi.portfolio.domain.model.Project

interface ViewProjectCallback {

    fun onProjectClicked(project: Project)

}