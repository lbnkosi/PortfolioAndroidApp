package za.co.lbnkosi.portfolio.domain.model

data class DynamicContent(
    var title: String = "",
    var subtitle: String = "",
    var shouldShowAnimation: Boolean = true,
    var aboutApp: String = "",
    var linkList: ArrayList<Link> = arrayListOf()
)