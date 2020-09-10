package codeit.student.kotlintemplate.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SocialDistancingControllerTest {
    @Test
    fun `test case 1`() {
        val expected = 20
        val actual   = SocialDistancingController.computeWays(8, 3, 1)
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `test case 2`() {
        val expected = 10
        val actual   = SocialDistancingController.computeWays(7, 3, 1)
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `test case 3`() {
        val expected = 6
        val actual   = SocialDistancingController.computeWays(6, 2, 2)
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `test case 4`() {
        val expected = 4
        val actual   = SocialDistancingController.computeWays(6, 3, 1)
        assertThat(expected).isEqualTo(actual)
    }
}