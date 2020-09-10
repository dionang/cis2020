package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.clean.CleanFloorRequest
import codeit.student.kotlintemplate.models.clean.CleanFloorResponse
import codeit.student.kotlintemplate.models.clean.Test
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CleanFloorController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/clean_floor")
    fun getMoves(@RequestBody request: CleanFloorRequest): CleanFloorResponse {
        logger.info("Request received $request")

        val response = CleanFloorResponse(
            request.tests.map { (testId, test) ->
                testId to computeLeastMoves(test.floor.toMutableList())
            }.toMap()
        )

        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun computeLeastMoves(floor: MutableList<Int>): Int {
            var pos = 0
            var moves = 0

            while (floor.any { it > 0 }) {
                val moveLeft = pos > 0 && floor[pos - 1] > 0

                if (moveLeft) pos -= 1
                else pos += 1

                if (floor[pos] > 0) floor[pos] -= 1
                else floor[pos] += 1

                moves += 1
            }

            return moves
        }
    }
}