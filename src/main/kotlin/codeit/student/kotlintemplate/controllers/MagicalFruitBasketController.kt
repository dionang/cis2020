package codeit.student.kotlintemplate.controllers

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
        val response = 5000
        logger.info("Returning result $response")
        return response
    }

}