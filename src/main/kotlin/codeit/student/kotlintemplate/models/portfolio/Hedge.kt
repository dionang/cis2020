package codeit.student.kotlintemplate.models.portfolio

import com.fasterxml.jackson.annotation.JsonProperty

data class Hedge (
    @JsonProperty("HedgePositionName")
    val HedgePositionName: String,
    @JsonProperty("OptimalHedgingRatio")
    val OptimalHedgingRatio: Double,
    @JsonProperty("NumFuturesContract")
    val NumFuturesContract: Int
)
