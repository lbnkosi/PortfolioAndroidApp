package za.co.lbnkosi.portfolio.domain.model

import za.co.lbnkosi.portfolio.domain.enums.ContactType

data class Contact(
    var id: String? = null,
    var uid: String = "",
    var alias: String = "",
    var primaryNumber: String = "",
    var secondaryNumber: String = "",
    var email: String = "",
    var contactType: String = ContactType.MANAGER.toString()
)