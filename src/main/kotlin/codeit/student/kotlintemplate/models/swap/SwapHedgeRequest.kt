package codeit.student.kotlintemplate.models.swap

data class SwapHedgeRequest (
    val time: Int,
    val bid: Double,
    val ask: Double,
    val accu_order: Int,
    val our_position: Int,
    val balance: Double,
    val client_balance: Double,
    val order: Int?,
    val run_id: String?,
    val tradedate_id: String?
)