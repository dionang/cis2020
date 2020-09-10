package codeit.student.kotlintemplate.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CleanFloorControllerTest {

    @Test
    fun `test case 1`() {
        val expected = 8
        val actual   = CleanFloorController.computeLeastMoves(mutableListOf(0,1,2,3,0))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 2`() {
        val expected = 11
        val actual   = CleanFloorController.computeLeastMoves(mutableListOf(1,1,1,1,1))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 3`() {
        val expected = 4
        val actual   = CleanFloorController.computeLeastMoves(mutableListOf(0,2,2,0,0))
        assertThat(actual).isEqualTo(expected)
    }
}