package com.wooheet.microservices

import com.wooheet.microservices.service.GreetingsService
import org.springframework.stereotype.Service
import java.util.*


@Service
class GreetingsServiceImpl : GreetingsService {
  companion object {
    private val greetingsMessages = arrayOf("Hello", "Olá", "Namaste", "Hola")
  }

  fun random(max: Int): Int = Random().nextInt(max) + 1

  override fun getGreeting(): String = greetingsMessages[random(4)]
}
