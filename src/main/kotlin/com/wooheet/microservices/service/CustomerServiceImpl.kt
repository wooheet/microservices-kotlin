package com.wooheet.microservices.service

import com.wooheet.microservices.domain.Customer
import com.wooheet.microservices.domain.Customer.Telephone
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {
  companion object {
    val initialCustomers = arrayOf(
        Customer(1, "Kotlin"),
        Customer(2, "Spring"),
        Customer(3, "Microservice", Telephone("+82", "7123456789"))
    )
  }

  val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))

  override fun getCustomer(id: Int) = customers[id]

  override fun deleteCustomer(id: Int) {
    customers.remove(id)
  }

  override fun createCustomer(customer: Customer) {
    customers[customer.id] = customer
  }

  override fun updateCustomer(id: Int, customer: Customer) {
    deleteCustomer(id)
    createCustomer(customer)
  }

  override fun searchCustomers(nameFilter: String): List<Customer> =
      customers.filter {
        it.value.name.contains(nameFilter, true)
      }.map(Map.Entry<Int, Customer>::value).toList()
}
