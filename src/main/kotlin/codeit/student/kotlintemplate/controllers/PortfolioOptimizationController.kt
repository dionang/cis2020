package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.portfolio.Hedge
import codeit.student.kotlintemplate.models.portfolio.PortfolioOptimizationRequest
import codeit.student.kotlintemplate.models.portfolio.PortfolioOptimizationResponse
import codeit.student.kotlintemplate.models.portfolio.Test
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedReader
import java.math.BigDecimal
import java.math.RoundingMode
import javax.servlet.http.HttpServletRequest
import kotlin.math.roundToInt

@RestController
class PortfolioOptimizationController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/optimizedportfolio")
    fun evaluate(@RequestBody request: PortfolioOptimizationRequest): PortfolioOptimizationResponse {
//        print(request.inputStream.bufferedReader().use(BufferedReader::readText))
        logger.info("Request received $request")
        val response = PortfolioOptimizationResponse(
            request.inputs.map { evaluate(it) }
        )
        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun evaluate(test: Test): Hedge {
            val hedges = test.IndexFutures.map { future ->
                var optimalHedgeRatio = future.CoRelationCoefficient * test.Portfolio.SpotPrcVol / future.FuturePrcVol
                optimalHedgeRatio = BigDecimal.valueOf(optimalHedgeRatio).setScale(3, RoundingMode.HALF_UP).toDouble()

                val numContracts = optimalHedgeRatio * test.Portfolio.Value / (future.IndexFuturePrice * future.Notional)

                Hedge(future.Name, optimalHedgeRatio, BigDecimal.valueOf(optimalHedgeRatio).setScale(0, RoundingMode.HALF_EVEN).toInt()) to future
            }

            return hedges.minWith(
                compareBy(
                    { it.first.OptimalHedgeRatio },
                    { it.second.FuturePrcVol },
                    { it.first.NumFuturesContract }
                )
            )!!.first
        }
    }
}