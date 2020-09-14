package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.farming.IntelligentFarmingRequest
import codeit.student.kotlintemplate.models.slsm.SLSMRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import javax.servlet.http.HttpServletRequest
import kotlin.math.roundToInt

class IntelligentFarmingController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/intelligent-farming")
    fun getDiceRolls(@RequestBody request: HttpServletRequest): List<Int> {
        val jsonString = request.inputStream.toString()
        logger.info("Request received $jsonString")

//        val response = SLSMController.getOptimalDiceRolls(request.boardSize, request.players, request.jumps)
//        logger.info("Returning result $response")
        return emptyList()
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