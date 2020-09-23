package codeit.student.kotlintemplate.models.portfolio

import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.fasterxml.jackson.databind.PropertyNamingStrategy.UpperCamelCaseStrategy

@JsonNaming(UpperCamelCaseStrategy::class)
data class Hedge (
    val HedgePositionName: String,
    val OptimalHedgeRatio: Double,
    val NumFuturesContract: Int
)
