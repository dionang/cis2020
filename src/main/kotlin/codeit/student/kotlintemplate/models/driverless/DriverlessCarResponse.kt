package codeit.student.kotlintemplate.models.driverless

data class DriverlessCarResponse (
    val gameId: String,
    val instructions: List<Instruction>
)