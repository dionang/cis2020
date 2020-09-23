package codeit.student.kotlintemplate.models.portfolio

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
data class Hedge (
    val HedgePositionName: String,
    val OptimalHedgingRatio: Double,
    val NumFuturesContract: Int
)
