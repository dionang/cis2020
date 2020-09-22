package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.swap.SwapHedgeRequest
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SwapHedgeController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/swaphedge")
    fun evaluate(@RequestBody request: SwapHedgeRequest): Map<String, Int> {
        logger.info("Request received $request")
        val response = mapOf(
            "order" to getAction(request)
        )
        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun getAction(request: SwapHedgeRequest): Int {
            if (request.time == 0) return 0

            return request.order / 2
        }
    }
}