package com.wooheet.microservice.route

import com.wooheet.microservice.handler.CustomerHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class CustomerRouter(private val customerHandler: CustomerHandler) {
  @Bean
  fun customerRoutes() = router {
    "/customer".nest {
      GET("/{id}", customerHandler::get)
      POST("/", customerHandler::create)
      DELETE("/{id}", customerHandler::delete)
    }
    "/customers".nest {
      GET("/", customerHandler::search)
    }
  }
}
