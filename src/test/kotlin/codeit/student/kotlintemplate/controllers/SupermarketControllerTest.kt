package codeit.student.kotlintemplate.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SupermarketControllerTest {
    @Test
    fun `simple test case`() {
        val expected = 3
        val actual   = SupermarketController.getShortestPath(
            maze = listOf(
                listOf(0, 0),
                listOf(1, 0)
            ),
            start = Pair(0,0),
            end = Pair(1,1)
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 1`() {
        val expected = 29
        val actual   = SupermarketController.getShortestPath(
            maze = listOf(
                listOf(1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1),
                listOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
                listOf(1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1),
                listOf(1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1),
                listOf(1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1),
                listOf(1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1),
                listOf(1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1),
                listOf(1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1),
                listOf(1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1),
                listOf(1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1),
                listOf(1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1)
            ),
            start = Pair(3,0),
            end = Pair(1,10)
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 2`() {
        val expected = 13
        val actual   = SupermarketController.getShortestPath(
            maze = listOf(
                listOf(1, 1, 1, 0, 1, 1, 1, 1, 1),
                listOf(1, 1, 1, 0, 0, 0, 0, 0, 1),
                listOf(1, 1, 1, 0, 1, 1, 1, 0, 1),
                listOf(1, 1, 1, 0, 0, 0, 1, 0, 1),
                listOf(1, 1, 1, 1, 1, 0, 1, 0, 1),
                listOf(1, 0, 0, 0, 1, 0, 1, 0, 1),
                listOf(1, 0, 1, 1, 1, 0, 1, 0, 1),
                listOf(1, 0, 0, 0, 0, 0, 1, 0, 1),
                listOf(1, 1, 1, 1, 1, 1, 1, 0, 1)
            ),
            start = Pair(3,0),
            end = Pair(7,8)
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 3`() {
        val expected = 9
        val actual   = SupermarketController.getShortestPath(
            maze = listOf(
                listOf(1, 1, 1, 0, 1, 1, 1),
                listOf(1, 0, 1, 0, 0, 0, 1),
                listOf(1, 0, 0, 0, 1, 0, 1),
                listOf(1, 1, 0, 1, 1, 1, 1),
                listOf(1, 0, 0, 0, 0, 1, 1),
                listOf(1, 1, 1, 1, 0, 1, 1)
            ),
            start = Pair(3,0),
            end = Pair(4,5)
        )
        assertThat(actual).isEqualTo(expected)
    }
}