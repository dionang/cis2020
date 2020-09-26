package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.YinYangRequest
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import kotlin.math.max

@RestController
class YinYangController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/yin-yang")
    fun evaluate(@RequestBody request: YinYangRequest): Map<String, BigDecimal> {
        logger.info("Request received $request")

        val response = mapOf(
            "result" to calculateEV(request.elements, request.number_of_operations, mutableMapOf())
        )

        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun calculateEV(elements: String, numberOfOperations: Int, memo: MutableMap<Pair<String, Int>, BigDecimal>): BigDecimal {
            // save result in memo
            val combination = Pair(elements, numberOfOperations)
            if (memo.contains(combination)) {
                return memo.getValue(combination)
            }

            // base case: number of operations = 1
            if (numberOfOperations == 1) {
                var prob = BigDecimal.ZERO.setScale(10)
                for (i in elements.indices) {
                    val left = elements[i]
                    val right = elements[elements.length - 1 - i]

                    prob += if (left == 'y' && right == 'y') BigDecimal.ZERO else BigDecimal.ONE
                }

                val result = prob / BigDecimal(elements.length)
                memo[combination] = result
                return result
            } else {
                // recurse to children
                var prob = BigDecimal.ZERO
                for (i in elements.indices) {
                    val left = elements[i]
                    val right = elements[elements.length - 1 - i]

                    val child1 = elements.substring(0, i) + elements.substring(i+1)
                    val child2 = elements.substring(0, elements.length - 1 - i) + elements.substring(elements.length - i)

                    if (left == right) {
                        // adds the result with the best ev (take letter from either left or right)
                        prob += calculateEV(child1, numberOfOperations - 1, memo).max(calculateEV(child2, numberOfOperations - 1, memo))

                        // adds ev of selecting Yang for current string
                        if (left == 'Y')
                            prob += BigDecimal.ONE
                    } else if (left == 'Y') {
                        prob += BigDecimal.ONE + calculateEV(child1, numberOfOperations - 1, memo)
                    } else {
                        prob += BigDecimal.ONE + calculateEV(child2, numberOfOperations - 1, memo)
                    }
                }

                val result = prob / BigDecimal(elements.length)
                memo[combination] = result
                return result
            }
        }
    }
}