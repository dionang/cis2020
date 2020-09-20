package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.geometry.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RevisitGeometryControllerTest {
    @Test
    fun `test case 1`() {
        val expected = listOf(
            Point(72.0, 108.0),
            Point(45.52, 97.41)
        )

        val actual = RevisitGeometryController.getIntersectionPoints(
            shapeCoordinates = listOf(
                Point(21.0, 70.0),
                Point(72.0, 70.0),
                Point(72.0, 127.0)
            ),
            lineCoordinates = listOf(
                Point(-58.0, 56.0),
                Point(-28.0, 68.0)
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 2`() {
        val expected = listOf(
            Point(60.0, -18.0),
            Point(71.0, -4.25)
        )

        val actual = RevisitGeometryController.getIntersectionPoints(
            shapeCoordinates = listOf(
                Point(-21.0, -18.0),
                Point(71.0, -18.0),
                Point(71.0, 71.0),
                Point(-21.0, 71.0)
            ),
            lineCoordinates = listOf(
                Point(68.0, -8.0),
                Point(108.0, 42.0)
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 3`() {
        val expected = emptyList<Point>()

        val actual = RevisitGeometryController.getIntersectionPoints(
            shapeCoordinates = listOf(
                Point(63.0, 26.0),
                Point(115.0, 26.0),
                Point(115.0, 54.0),
                Point(63.0, 54.0)
            ),
            lineCoordinates = listOf(
                Point(-88.0, 85.0),
                Point(-58.0, 97.0)
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 4`() {
        val expected = emptyList<Point>()

        val actual = RevisitGeometryController.getIntersectionPoints(
            shapeCoordinates = listOf(
                Point(33.0, 61.0),
                Point(93.0, 81.0)
            ),
            lineCoordinates = listOf(
                Point(-88.0, 85.0),
                Point(-58.0, 97.0)
            )
        )

        println(actual)
        assertThat(actual).isEqualTo(expected)
    }
}
