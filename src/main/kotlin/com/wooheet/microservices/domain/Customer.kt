package com.wooheet.microservices.domain

data class Customer(var id: Int = 0, var name: String = "", var telephone: Telephone? = null) {
  data class Telephone(var countryCode: String = "", var telephoneNumber: String = "")
}
