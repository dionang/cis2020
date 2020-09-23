package codeit.student.kotlintemplate.models.portfolio

data class Hedge (
    @JsonProperty("HedgePositionName")
    val HedgePositionName: String,
    @JsonProperty("OptimalHedgingRatio")
    val OptimalHedgingRatio: Double,
    @JsonProperty("NumFuturesContract")
    val NumFuturesContract: Int
)
