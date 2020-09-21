package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.geometry.Point
import codeit.student.kotlintemplate.models.geometry.RevisitGeometryRequest
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.math.max
import kotlin.math.min

@RestController
class RevisitGeometryController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/revisitgeometry")
    fun evaluate(@RequestBody request: RevisitGeometryRequest): List<Point> {
        logger.info("Request received $request")

        val response = getIntersectionPoints(request.shapeCoordinates, request.lineCoordinates)

        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun getIntersectionPoints(shapeCoordinates: List<Point>, lineCoordinates: List<Point>): List<Point> {
            val pairwisePoints = shapeCoordinates.toMutableList()
            if (shapeCoordinates.size > 2)
                pairwisePoints += shapeCoordinates.first()

            return pairwisePoints.
                zipWithNext { p1, p2 ->
                    findIntersectionPoint(p1, p2, lineCoordinates.first(), lineCoordinates.last())
                }
                .mapNotNull { it }
                .map { it.round() }
        }

        fun findIntersectionPoint(p1: Point, p2: Point, l1: Point, l2: Point): Point? {
            // line is vertical
            val xRange = min(p1.x, p2.x)..max(p1.x, p2.x)
            val yRange = min(p1.y, p2.y)..max(p1.y, p2.y)

            if (l1.x == l2.x) {

                // check if x of line is within bounds of the segment, if exist, intersection point exists
                return if (l1.x in xRange) {

                    // assume line segment is not vertical, calculate gradient m
                    val pm = (p1.y - p2.y)/(p1.x - p2.x)

                    // calculate intercept c from y = mx + c -> c = y - mx
                    val pc = p1.y - pm * p1.x

                    // calculate intersection point's y value and return point
                    Point(l1.x, pm * l1.x + pc)
                } else null

            } else {
                val lm = (l1.y - l2.y)/(l1.x - l2.x)
                val lc = l1.y - lm * l1.x

                // check if segment is vertical
                if (p1.x == p2.x) {

                    // get corresponding y value for line given x
                    val ly = lm * p1.x + lc
                    return if (ly in yRange) { Point(p1.x, ly) }
                    else null

                } else {
                    val pm = (p1.y - p2.y)/(p1.x - p2.x)
                    val pc = p1.y - pm * p1.x

                    // iy = pm * ix + pc = lm * ix + lc
                    // pm * ix - lm * ix = lc - pc
                    // ix = (lc - pc) / (pm - lm)
                    val ix = (lc - pc) / (pm - lm)
                    val iy = pm * ix + pc

                    return if (ix in xRange && iy in yRange) { Point(ix, iy) }
                    else null
                }
            }
        }
    }
}