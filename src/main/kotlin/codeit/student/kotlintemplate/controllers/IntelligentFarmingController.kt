package codeit.student.kotlintemplate.controllers

import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.roundToInt

@RestController
class IntelligentFarmingController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/intelligent-farming")
    fun getGeneSequence(request: HttpEntity<String>): List<Int> {
        val jsonString = request.body
        logger.info("Request received $jsonString")

//        val response = SLSMController.getOptimalDiceRolls(request.boardSize, request.players, request.jumps)
//        logger.info("Returning result $response")
        return emptyList()
    }

    private fun getMaxDRIGeneSequence(geneSequence: String): List<Int> {
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