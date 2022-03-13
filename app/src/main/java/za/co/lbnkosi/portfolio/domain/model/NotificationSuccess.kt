package za.co.lbnkosi.portfolio.domain.model

import com.google.gson.annotations.SerializedName

data class NotificationSuccess(
    @SerializedName("message_id") var messageId: Long? = null
)