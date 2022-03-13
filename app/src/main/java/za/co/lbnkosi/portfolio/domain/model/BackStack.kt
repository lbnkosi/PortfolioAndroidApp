package za.co.lbnkosi.portfolio.domain.model

data class BackStack(
    var isPortfolio: Boolean = false,
    var isHome: Boolean = false,
    var isAbout: Boolean = false,
    var isSettings: Boolean = false
)
