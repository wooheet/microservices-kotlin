package com.wooheet.microservices.route

import com.wooheet.microservices.handler.CustomerHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class CustomerRouter(private val customerHandler: CustomerHandler) {
  @Bean
  fun customerRoutes() = router {
    "/ping".nest {
      GET("", customerHandler::pong)
    }
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
