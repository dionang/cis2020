package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.social.SocialDistancingRequest
import codeit.student.kotlintemplate.models.social.SocialDistancingResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SocialDistancingController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/social_distancing")
    fun getWays(@RequestBody request: SocialDistancingRequest): SocialDistancingResponse {
        logger.info("Request received $request")
        val response = evaluate(request)
        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun evaluate(request: SocialDistancingRequest): SocialDistancingResponse {
            return SocialDistancingResponse(
                answers = request.tests.map { (testId, test) ->
                    testId to computeWays(test.seats, test.people, test.spaces)
                }.toMap()
            )
        }

        fun computeWays(seats: Int, people: Int,  spaces: Int): Int {
            val minSeats = people * (spaces + 1) - spaces // fence posting spaces with people
            val extraSpaces = seats - minSeats

            // nCr, n is number of people + 1 (positions to insert spaces), r = no. of spaces
            return factorial(people+extraSpaces) / factorial(extraSpaces) / factorial(people)

        }

        private fun factorial(n: Int): Int {
            return (1..n).reduce { acc, i -> acc * i }
        }
    }
}