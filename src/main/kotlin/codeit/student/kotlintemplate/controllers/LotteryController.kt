package codeit.student.kotlintemplate.controllers

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.roundToInt

@RestController
class LotteryController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/lottery")
    fun getInstructions(): List<Int> {
        val response = calculateGuesses()
        logger.info("Returning result $response")
        return response
    }

    private fun calculateGuesses(): List<Int> {
        val numbers = 10
        val results = (1..100).map { diff ->
            (1..numbers).map { it*diff%100 }
        }

        return (0..9).map{ i ->
            var sum = 0
            (0..99).forEach { j ->
                sum += results[j][i]
            }

            (sum.toDouble() / 100).roundToInt()
        }
    }
}