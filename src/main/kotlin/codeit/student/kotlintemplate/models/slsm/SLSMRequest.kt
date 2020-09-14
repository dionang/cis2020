package codeit.student.kotlintemplate.models.slsm

data class SLSMRequest(
    val boardSize: Int,
    val players: Int,
    val jumps: List<Jump>
)
