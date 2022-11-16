package com.wooheet.microservices.handler


import com.wooheet.microservices.domain.Customer
import com.wooheet.microservices.service.CustomerService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.net.URI

@Component
class CustomerHandler(val customerService: CustomerService) {
  @Value("\${microservice.config.greetings}")
  private lateinit var pingpong: String

  fun get(serverRequest: ServerRequest) =
      customerService.getCustomer(serverRequest.pathVariable("id").toInt())
          .flatMap { ok().body(fromValue(it)) }
          .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

  fun create(serverRequest: ServerRequest) =
      customerService.createCustomer(serverRequest.bodyToMono()).flatMap {
        created(URI.create("Customer ID: ${it.id}")).build()
      }

  fun delete(serverRequest: ServerRequest) =
      customerService.deleteCustomer(serverRequest.pathVariable("id").toInt())
          .flatMap {
            if (it) ok().build()
            else status(HttpStatus.NOT_FOUND).build()
          }

  fun search(serverRequest: ServerRequest) =
      ok().body(customerService.searchCustomers(serverRequest.queryParam("nameFilter")
          .orElse("")), Customer::class.java)
  fun pong(serverRequest: ServerRequest) = ok()
      .body(pingpong.toMono(), String::class.java)

}
