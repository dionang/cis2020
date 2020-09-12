package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.salad.SaladSpreeRequest
import codeit.student.kotlintemplate.models.salad.SaladSpreeResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SaladSpreeController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/salad-spree")
    fun getMoves(@RequestBody request: SaladSpreeRequest): SaladSpreeResponse {
        logger.info("Request received $request")

        val response = SaladSpreeResponse(
            getMinAmountSpent(request.numberOfSalads, request.saladPricesStreetMap)
        )

        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun getMinAmountSpent(numberOfSalads: Int, saladPricesStreetMap: List<List<String>>): Int {
            var min = Int.MAX_VALUE

            for (street in saladPricesStreetMap) {
                var consecutive = 0
                var runningTotal = 0

                street.forEachIndexed { index, store ->
                    if (store == "X") {
                        consecutive = 0
                        runningTotal = 0
                    } else  {
                        consecutive += 1
                        runningTotal += store.toInt()

                        if (consecutive > numberOfSalads) {
                            runningTotal -= street[index - numberOfSalads].toInt()
                        }

                        if (consecutive >= numberOfSalads && runningTotal < min) {
                            min = runningTotal
                        }
                    }
                }
            }

            return if (min != Int.MAX_VALUE) min else 0
        }
    }
}