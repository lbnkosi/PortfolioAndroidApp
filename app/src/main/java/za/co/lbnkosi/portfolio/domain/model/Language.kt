package za.co.lbnkosi.portfolio.domain.model

import za.co.lbnkosi.portfolio.domain.enums.Fluency

data class Language(
    var id: String? = null,
    var uid: String = "",
    var name: String = "",
    var writingFluency: String = Fluency.EXCELLENT.toString(),
    var speakingFluency: String = Fluency.EXCELLENT.toString(),
    var readingFluency: String = Fluency.EXCELLENT.toString()
)