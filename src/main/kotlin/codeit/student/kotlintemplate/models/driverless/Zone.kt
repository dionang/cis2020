package codeit.student.kotlintemplate.models.driverless


data class Zone(
    val maxSpeed: Int,
    val from: Map<String, Double>,
    val to: Map<String, Double>
)