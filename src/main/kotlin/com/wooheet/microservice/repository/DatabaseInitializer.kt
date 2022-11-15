//package com.wooheet.microservices.repository
//
//import com.wooheet.microservices.domain.Customer
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.data.mongodb.core.ReactiveMongoOperations
//import org.springframework.stereotype.Component
//import javax.annotation.PostConstruct
//
//@Component
//class DatabaseInitializer {
//    @Autowired
//    lateinit var customerRepository: CustomerRepository
//
//    companion object {
//        val initialCustomers = listOf(
//            Customer(1, "Kotlin"),
//            Customer(2, "Spring"),
//            Customer(3, "Microservice", Customer.Telephone("+82", "12309123"))
//        )
//    }
//
//    @PostConstruct
//    fun initData() {
////        mongoOperations.collectionExists("Customers-test").subscribe {
////            if (it != true)
////            mongoOperations.createCollection("Customers-test").subscribe {
////                println("Customers collections created")
////            } else println("Customers collections already exist")
////        }
//        customerRepository.saveAll(initialCustomers).subscribe {
//            println("Default customers created")
//        }
//    }
//}