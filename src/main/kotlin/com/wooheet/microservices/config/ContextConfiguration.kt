package com.wooheet.microservices.config


import com.wooheet.microservices.services.accounts.AccountService
import com.wooheet.microservices.services.accounts.AccountServiceImpl
import com.wooheet.microservices.services.cusmers.CustomerService
import com.wooheet.microservices.services.cusmers.CustomerServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ContextConfiguration {
  @Bean
  fun accountService() : AccountService = AccountServiceImpl()

//  @Bean
//  fun customerService() : CustomerService = CustomerServiceImpl()
//
//  @Bean
//  fun customerController(customerService: CustomerService) = CustomerController(customerService)
}
