package codeit.student.kotlintemplate.models.driverless

data class DriverlessCarRequest(
    val gameId: String,
    val roads: List<Road>,
    val start: Map<String, Double>,
    val finish: Map<String, Double>,
    val vehicle: Vehicle
)