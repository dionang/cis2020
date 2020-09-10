package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.social.Test
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.Math.max

@RestController
class SocialDistancingController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/social_distancing")
    fun getWays(@RequestBody request: Map<String, Test>): Map<String, Int> {
        logger.info("Request received $request")

        val response = request.map { (testId, test) ->
            testId to computeWays(test.seats, test.people, test.spaces)
        }.toMap()

        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun computeWays(seats: Int, people: Int,  spaces: Int): Int {
            val minSeats = people * (spaces + 1) - spaces // fence posting spaces with people
            val extraSpaces = seats - minSeats

            // nCr, n is number of people + 1 (positions to insert spaces), r = no. of spaces
            return combination(people + extraSpaces, extraSpaces)
        }

        private fun combination(n: Int, r: Int): Int {
            val x = max(r, n-r)
            var res = 1

            for (i in x+1..n) {
                res *= i
                res /= i-x
            }
            return res
        }
    }
}