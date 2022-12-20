package com.wooheet.microservices.services.accounts

import com.wooheet.microservices.domains.accounts.model.Account


interface AccountService {
  fun getAccountsByCustomer(customerId: Int): List<Account>
}