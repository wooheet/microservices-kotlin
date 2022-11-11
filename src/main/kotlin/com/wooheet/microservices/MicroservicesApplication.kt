package com.wooheet.microservices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
abstract class MicroservicesApplication {
    @Bean
    @ConditionalOnExpression("#{'\${service.type}'=='simple'}")
    fun exampleService(): ServiceInterface = ExampleService()

    @Bean
    @ConditionalOnExpression("#{'\${service.type}'=='advance'}")
    fun advanceService(): ServiceInterface = AdvanceService()

}

@Controller
class FirstController {
    @Autowired
    lateinit var service: ServiceInterface

    // "/user/{name}" -> ["/user/{name}"] 열거형으로 매핑을 해줘야한다.
    @RequestMapping(value = ["/user/{name}"], method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun hello(@PathVariable name: String) = service.getHello(name)
}

fun main(args: Array<String>) {
    runApplication<MicroservicesApplication>(*args)
}
