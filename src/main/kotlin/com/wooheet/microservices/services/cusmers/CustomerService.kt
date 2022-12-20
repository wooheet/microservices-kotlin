package com.wooheet.microservices.services.cusmers

import com.wooheet.microservices.domains.customers.model.Customer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {
  fun getCustomer(id: Int): Mono<Customer>
  fun createCustomer(customer: Mono<Customer>): Mono<Customer>
  fun deleteCustomer(id: Int): Mono<Boolean>
  fun searchCustomers(nameFilter: String): Flux<Customer>

  fun getAllCustomers(): Flux<Customer>
}