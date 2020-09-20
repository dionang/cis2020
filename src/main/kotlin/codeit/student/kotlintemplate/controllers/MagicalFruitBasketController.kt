package codeit.student.kotlintemplate.controllers

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MagicalFruitBasketController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/fruitbasket")
    fun getGuess(): Int {
//        logger.info("Request received $request")
        val response = 0
        logger.info("Returning result $response")
        return response
    }

}