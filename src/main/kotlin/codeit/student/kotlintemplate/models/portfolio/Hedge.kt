package codeit.student.kotlintemplate.models.portfolio

import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy

@JsonNaming(SnakeCaseStrategy::class)
data class Hedge (
    val HedgePositionName: String,
    val OptimalHedgingRatio: Double,
    val NumFuturesContract: Int
)
