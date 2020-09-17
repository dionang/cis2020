package codeit.student.kotlintemplate.models.portfolio

data class Hedge (
    val HedgePositionName: String,
    val OptimalHedgingRatio: Double,
    val NumFuturesContract: Int
)
