package codeit.student.kotlintemplate.models.geometry

data class Point (
    val x: Double,
    val y: Double
) {
    fun round(): Point {
        return Point(
            x = String.format("%.2f", x).toDouble(),
            y = String.format("%.2f", y).toDouble()
        )
    }
}
