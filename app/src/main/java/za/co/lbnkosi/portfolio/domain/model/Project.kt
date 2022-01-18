package za.co.lbnkosi.portfolio.domain.model


data class Project(
    var id: String? = null,
    var uid: String = "",
    var name: String = "",
    var platform: String = "",
    var downloadLink: String = "",
    var githubLink: String = "",
    var description: String = "",
    var image: String = ""
)