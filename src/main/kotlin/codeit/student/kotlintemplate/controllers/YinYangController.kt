package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.YinYangRequest
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.math.max

@RestController
class YinYangController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/yin-yang")
    fun evaluate(@RequestBody request: YinYangRequest): Map<String, Double> {
        logger.info("Request received $request")

        val response = mapOf(
            "result" to calculateEV(request.elements, request.number_of_operations)
        )

        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun calculateEV(elements: String, numberOfOperations: Int, memo: MutableMap<Pair<String, Int>, Double> = mutableMapOf()): Double {
            // save result in memo
            if (memo.contains(Pair(elements, numberOfOperations))) {
                return memo.getValue(Pair(elements, numberOfOperations))
            }

            // base case: number of operations = 1
            if (numberOfOperations == 1) {
                var prob = 0.0
                for (i in elements.indices) {
                    val left = elements[i]
                    val right = elements[elements.length - 1 - i]

                    prob += if (left == 'y' && right == 'y') 0.0 else 1.0
                }

                return prob / elements.length
            } else {
                // recurse to children
                var prob = 0.0
                for (i in elements.indices) {
                    val left = elements[i]
                    val right = elements[elements.length - 1 - i]

                    val child1 = elements.substring(0, i) + elements.substring(i+1)
                    val child2 = elements.substring(0, elements.length - 1 - i) + elements.substring(elements.length - i)


                    if (left == right) {
                        // adds the result with the best ev (take letter from either left or right)
                        prob += max(calculateEV(child1, numberOfOperations - 1), calculateEV(child2, numberOfOperations - 1))

                        // adds ev of selecting Yang for current string
                        if (left == 'Y')
                            prob += 1
                    } else if (left == 'Y') {
                        prob += 1 + calculateEV(child1, numberOfOperations - 1)
                    } else {
                        prob += 1 + calculateEV(child2, numberOfOperations - 1)
                    }
                }

                return prob / elements.length
            }
        }
    }
}