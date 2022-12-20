package com.wooheet.microservices

import com.wooheet.microservices.handler.customers.CustomerHandler
import com.wooheet.microservices.route.CustomerRouter
import com.wooheet.microservices.services.cusmers.CustomerService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient

@RunWith(SpringRunner::class)
@SpringBootTest
class CustomerServiceTest {
  @Autowired
  lateinit var customerService: CustomerService

  @Autowired
  lateinit var customerHandler: CustomerHandler

  @Test
  fun `we should get a customer with a valid id`() {
//    val customer = customerService.getCustomer(1)
//    customer.`should not be null`()
//
//    customer?.name `should be` "Kotlin"

//      customerService.getCustomer(1).flatMap {
//          it.`should not be null`()
//          it?.name `should be` "Kotlin"
//      }

      val client = WebTestClient.bindToRouterFunction(
          CustomerRouter(customerHandler).customerRoutes()).build()

      client.get()
          .uri("/ping")
          .exchange()
          .expectStatus()
          .isOk

//      client.get()
//          .uri("/customer/1")
//          .exchange()
//          .expectBody()
//          .json(`"id": 1, "name": "Kotlin"`)
  }

//  @Test
//  fun `we should get all customers`() {
//    val customers = customerService.getAllCustomers()
//    customers.size `should equal to` 3
//    customers.size `should be greater than` 0
//    customers.size `should be less or equal to` 3
//    customers.size `should be in range` 1..3
//  }
}
