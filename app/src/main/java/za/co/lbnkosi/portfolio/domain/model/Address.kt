package za.co.lbnkosi.portfolio.domain.model

import za.co.lbnkosi.portfolio.domain.enums.AddressType

data class Address(
    var id: String? = null,
    var uid: String = "",
    var alias: String = "",
    var street: String = "",
    var suburb: String = "",
    var city: String = "",
    var province: String = "",
    var postalCode: String = "",
    var addressType: String = AddressType.HOME.toString(),
)