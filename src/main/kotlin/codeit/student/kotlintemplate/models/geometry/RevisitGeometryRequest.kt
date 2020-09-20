package codeit.student.kotlintemplate.models.geometry

data class RevisitGeometryRequest (
    val shapeCoordinates: List<Point>,
    val lineCoordinates:  List<Point>
)