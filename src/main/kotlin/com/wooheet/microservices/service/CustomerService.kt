package com.wooheet.microservices.service

import com.wooheet.microservices.domain.Customer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {
  fun getCustomer(id: Int): Customer?
  fun getAllCustomers(): List<Customer>
}
