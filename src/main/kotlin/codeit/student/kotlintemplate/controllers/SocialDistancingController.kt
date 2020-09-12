package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.social.SocialDistancingRequest
import codeit.student.kotlintemplate.models.social.SocialDistancingResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.Math.max

@RestController
class SocialDistancingController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/social_distancing")
    fun getWays(@RequestBody request: SocialDistancingRequest): SocialDistancingResponse {
        logger.info("Request received $request")

        val response = SocialDistancingResponse(
            request.tests.map { (testId, test) ->
                testId to computeWays(test.seats, test.people, test.spaces)
            }.toMap()
        )

        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun computeWays(seats: Int, people: Int,  spaces: Int): Long {
            val minSeats = people * (spaces + 1) - spaces // fence posting spaces with people
            val extraSpaces = seats - minSeats

            // when inserting spaces between 2 people, doesn't matter which position
            // if there's for example 3 spaces between 2 people, ways to insert is 1, as the arrangement of spaces dont matter
            // therefore, only need to consider combination of ways of arranging people and the extra spaces
            return combination(people + extraSpaces, extraSpaces)
        }

        private fun combination(n: Int, r: Int): Long {
            val x = max(r, n-r)
            var res: Long = 1

            for (i in x+1..n) {
                res *= i
                res /= i-x
            }
            return res
        }
    }
}