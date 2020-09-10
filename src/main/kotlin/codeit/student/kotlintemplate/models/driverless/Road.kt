package codeit.student.kotlintemplate.models.driverless

data class Road(
    val name: String,
    val type: String,
    val from: Map<String, Double>,
    val to: Map<String, Double>,
    val maxSpeed: Double,
    val zones: List<Zone>
)