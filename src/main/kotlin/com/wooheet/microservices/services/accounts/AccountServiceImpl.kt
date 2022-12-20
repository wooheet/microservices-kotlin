package com.wooheet.microservices.services.accounts

import com.wooheet.microservices.domains.accounts.model.Account


class AccountServiceImpl : AccountService {
  override fun getAccountsByCustomer(customerId: Int): List<Account>
      = listOf(Account(1, 125F), Account(2, 500F))
}
