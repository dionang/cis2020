package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.portfolio.Hedge
import codeit.student.kotlintemplate.models.portfolio.PortfolioOptimizationRequest
import codeit.student.kotlintemplate.models.portfolio.PortfolioOptimizationResponse
import codeit.student.kotlintemplate.models.portfolio.Test
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PortfolioOptimizationController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/optimizedportfolio")
    fun getOptimalNumberOfBooks(@RequestBody request: PortfolioOptimizationRequest): PortfolioOptimizationResponse {
        logger.info("Request received $request")
        val response = PortfolioOptimizationResponse(
            request.inputs.map{ evaluate(it) }
        )
        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun evaluate(test: Test): Hedge {
            val hedges = test.IndexFutures.map { future ->
                val optimalHedgeRatio = future.CoRelationCoefficient * test.Portfolio.SpotPrcVol / future.FuturePrcVol
                val numContracts = optimalHedgeRatio * test.Portfolio.Value / (future.IndexFuturePrice * future.Notional)
                Hedge(future.Name, optimalHedgeRatio, numContracts.toInt())
            }

            println(hedges)
            return hedges.last()
        }
    }
}