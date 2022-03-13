package za.co.lbnkosi.portfolio.domain.model


data class Competency(
    var id: String? = null,
    var uid: String = "",
    var name: String = "",
    var rating: Int = 0,
    var description: String = ""
)