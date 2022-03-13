package za.co.lbnkosi.portfolio.domain.model

import za.co.lbnkosi.portfolio.domain.enums.WorkType
import java.util.*

data class Work(
    var id: String? = null,
    var uid: String = "",
    var alias: String = "",
    var companyName: String = "",
    var position: String = "",
    var startDate: Date = Date(),
    var endDate: Date = Date(),
    var currentPosition: Boolean = false,
    var logo: String = "",
    var description: String = "",
    var workType: String = WorkType.PERMANENT.toString()
)