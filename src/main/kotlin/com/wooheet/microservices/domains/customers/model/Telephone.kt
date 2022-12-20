package com.wooheet.microservices.domains.customers.model

data class Telephone(
    var countryCode: String = "",
    var telephoneNumber: String = ""
)