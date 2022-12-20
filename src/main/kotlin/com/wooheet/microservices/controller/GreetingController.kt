package com.wooheet.microservices.controller

import com.wooheet.microservices.services.greetings.GreetingsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {
  @Autowired
  lateinit var greetingService: GreetingsService

  @GetMapping("/hello")
  fun message() = greetingService.getGreeting()
}
