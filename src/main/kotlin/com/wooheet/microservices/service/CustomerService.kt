package com.wooheet.microservices.service

import com.wooheet.microservices.domain.Customer

interface CustomerService {
  fun getCustomer(id: Int): Customer? // null safe, java => Optional Objects
  fun createCustomer(customer: Customer)
  fun deleteCustomer(id: Int)
  fun updateCustomer(id: Int, customer: Customer)
  fun searchCustomers(nameFilter: String): List<Customer>
}
