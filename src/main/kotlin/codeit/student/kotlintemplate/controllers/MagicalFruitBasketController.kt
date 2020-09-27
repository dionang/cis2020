package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.fruitbasket.FruitBasket
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MagicalFruitBasketController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/fruitbasket", consumes = [MediaType.TEXT_PLAIN_VALUE])
    fun getGuess(@RequestBody request: String): Int {
        logger.info("Request received $request")
        val basket = jacksonObjectMapper().readValue(request, FruitBasket::class.java)
        println(basket)
//        val response = basket.getWeight()
        val response = 0
        logger.info("Returning result $response")
        return response
    }
}