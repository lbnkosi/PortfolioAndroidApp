package za.co.lbnkosi.portfolio.domain.model

import za.co.lbnkosi.portfolio.util.enums.EducationType
import java.util.*

data class Education(
    var id: String? = null,
    var uid: String = "",
    var alias: String = "",
    var name: String = "",
    var startDate: Date = Date(),
    var endDate: Date = Date(),
    var educationType: String = EducationType.CERTIFICATE.toString(),
    var enrolled: Boolean = false,
    var logo: String = ""
)