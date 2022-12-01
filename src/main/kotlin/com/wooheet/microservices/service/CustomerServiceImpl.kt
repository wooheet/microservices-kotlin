package com.wooheet.microservices.service

import com.wooheet.microservices.domain.Customer
import com.wooheet.microservices.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class CustomerServiceImpl : CustomerService {
  @Value("\${microservice.config.greetings}")
  private lateinit var pingpong: String

  @Autowired
  lateinit var customerRepository: CustomerRepository

  override fun getCustomer(id: Int) = customerRepository.findById(id)
  override fun createCustomer(customer: Mono<Customer>) = customerRepository.create(customer)
  override fun deleteCustomer(id: Int) = customerRepository.deleteById(id).map { it.deletedCount > 0 }
  override fun searchCustomers(nameFilter: String) = customerRepository.findCustomer(nameFilter)

}
