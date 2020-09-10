package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.clean.Test
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CleanFloorController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/social_distancing")
    fun getWays(@RequestBody request: Map<String, Test>): Map<String, Int> {
        logger.info("Request received $request")

        val response = request.map { (testId, test) ->
            testId to computeLeastMoves(test.floor)
        }.toMap()

        logger.info("Returning result $response")
        return response
    }

    companion object {
        private fun computeLeastMoves(floor: List<Int>): Int {
            val done = false
            var pos  = 0

//            while (done){
//                val moveLeft = pos > 0 && floor[pos-1] > 0
//
//                if (moveLeft) {
//                    pos -= 1
//                    floor[pos] -= 1
//                } else {
//                    pos += 1
//                    if (floor[pos] > 0) {
//                        floor[pos] -= 1
//                    } else {
//                        floor[pos] += 1
//                    }
//                }
//
//                val right = Integer.min(pos +1, )
//            }
            return pos
        }

    }
}