package za.co.lbnkosi.portfolio.ui.features.about

import za.co.lbnkosi.portfolio.domain.model.Link

interface LinkCallback {
    fun onLinkClick(link: Link)
}