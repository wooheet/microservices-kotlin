package com.wooheet.microservices

import org.springframework.stereotype.Service


interface ServiceInterface {
  fun getHello(name: String): String
}
