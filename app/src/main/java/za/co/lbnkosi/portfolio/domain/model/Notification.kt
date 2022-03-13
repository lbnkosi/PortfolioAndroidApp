package za.co.lbnkosi.portfolio.domain.model

data class Notification(
    var to: String = "",
    var data: NotificationBody = NotificationBody()
)