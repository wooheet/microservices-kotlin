package com.wooheet.microservices.domains.customers.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Customers")
data class Customer(
  var id: Int = 0,
  var name: String = "",
  var telephone:
  Telephone? = null
)