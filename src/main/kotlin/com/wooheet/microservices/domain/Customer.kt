package com.wooheet.microservices.domain

import org.springframework.data.mongodb.core.mapping.Document

data class Customer(
  var id: Int = 0,
  var name: String = "",
  var telephone:
  Telephone? = null
)