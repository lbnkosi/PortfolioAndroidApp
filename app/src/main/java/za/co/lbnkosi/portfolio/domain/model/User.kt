package za.co.lbnkosi.portfolio.domain.model

import za.co.lbnkosi.portfolio.util.enums.Gender
import java.util.*

data class User(
    var id: String? = null,
    var uid: String = "",
    var name: String = "",
    var secondName: String = "",
    var surname: String = "",
    var title: String = "",
    var profileImage: String = "",
    var gender: String = Gender.MALE.toString(),
    var dateOfBirth: Date = Date(),
    var resume: String = ""
)