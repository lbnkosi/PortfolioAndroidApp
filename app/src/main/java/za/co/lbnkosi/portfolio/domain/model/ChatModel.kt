package za.co.lbnkosi.portfolio.domain.model

import java.util.*

data class ChatModel(
    val message: String = "",
    val date: Date = Date(),
    val uid: String = "",
)