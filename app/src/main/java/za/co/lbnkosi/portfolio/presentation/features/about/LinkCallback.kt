package za.co.lbnkosi.portfolio.presentation.features.about

import za.co.lbnkosi.portfolio.domain.model.Link

interface LinkCallback {
    fun onLinkClick(link: Link)
}