package codeit.student.kotlintemplate.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ClusterControllerTest {
    @Test
    fun `getInfected method works as expected`() {
        val expected = listOf(Pair(1,1), Pair(3,3))
        val actual = ClusterController.getInfected(
            listOf(
                listOf("*", "*", "*", "*"),
                listOf("*", "1", "*", "*"),
                listOf("*", "*", "*", "*"),
                listOf("*", "*", "*", "1"),
                listOf("*", "*", "*", "*")
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 1`() {
        val expected = 2
        val actual = ClusterController.countClusters(
            listOf(
                listOf("*", "*", "*", "*"),
                listOf("*", "1", "*", "*"),
                listOf("*", "*", "*", "*"),
                listOf("*", "*", "*", "1"),
                listOf("*", "*", "*", "*")
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 2`() {
        val expected = 1
        val actual = ClusterController.countClusters(
            listOf(
                listOf("*", "*", "*", "*"),
                listOf("*", "1", "0", "*"),
                listOf("*", "*", "*", "*"),
                listOf("*", "*", "0", "0"),
                listOf("*", "*", "*", "*")
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 3`() {
        val expected = 1
        val actual = ClusterController.countClusters(
            listOf(
                listOf("*", "*", "*", "*"),
                listOf("*", "1", "0", "*"),
                listOf("*", "*", "*", "1"),
                listOf("*", "*", "0", "0"),
                listOf("*", "*", "*", "*")
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 4`() {
        val expected = 1
        val actual = ClusterController.countClusters(
            listOf(
                listOf("1", "0", "*", "*"),
                listOf("0", "0", "*", "0"),
                listOf("*", "*", "0", "*"),
                listOf("*", "*", "*", "*"),
                listOf("*", "*", "*", "*"),
                listOf("*", "0", "0", "*")
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 5`() {
        val expected = 2
        val actual = ClusterController.countClusters(
            listOf(
                listOf("*", "*", "*", "*", "*", "*", "*", "*", "*"),
                listOf("*", "0", "0", "0", "*", "*", "*", "*", "*"),
                listOf("*", "*", "1", "*", "*", "*", "*", "*", "*"),
                listOf("*", "0", "0", "0", "*", "*", "*", "*", "*"),
                listOf("*", "*", "*", "*", "0", "*", "*", "*", "*"),
                listOf("*", "*", "*", "*", "*", "0", "0", "*", "*"),
                listOf("*", "*", "*", "*", "1", "*", "*", "*", "0"),
                listOf("*", "*", "*", "*", "0", "*", "*", "0", "0"),
                listOf("*", "*", "*", "*", "*", "*", "*", "*", "*"),
                listOf("*", "*", "*", "*", "*", "*", "*", "*", "*"),
                listOf("*", "*", "*", "*", "*", "*", "*", "*", "*"),
                listOf("*", "*", "1", "0", "0", "*", "*", "*", "*"),
                listOf("*", "*", "*", "*", "*", "*", "*", "*", "*")
            )
        )

        assertThat(actual).isEqualTo(expected)
    }
}