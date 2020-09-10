package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.driverless.DriverlessCarRequest
import codeit.student.kotlintemplate.models.driverless.DriverlessCarResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DriverlessCarController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/driverless-car")
    fun getInstructions(@RequestBody request: DriverlessCarRequest): DriverlessCarResponse {
        logger.info("Request received $request")
        val response = evaluate(request)
        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun evaluate(request: DriverlessCarRequest): DriverlessCarResponse {
            return DriverlessCarResponse(
                gameId = request.gameId,
                instructions = listOf()
            )
        }
    }
}