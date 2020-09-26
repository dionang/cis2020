package codeit.student.kotlintemplate.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class YinYangControllerTest {
    @Test
    fun `simple test case`() {
        val expected = 1.0
        val actual   = YinYangController.calculateEV(
            elements = "yYY",
            numberOfOperations = 1
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `simple test case 2`() {
        val expected = 1
        val actual   = YinYangController.calculateEV(
            elements = "YYy",
            numberOfOperations = 1
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `simple test case 3`() {
        val expected = 2.0 / 3
        val actual   = YinYangController.calculateEV(
            elements = "Yyy",
            numberOfOperations = 1
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `simple test case 4`() {
        val expected = 1.5
        val actual   = YinYangController.calculateEV(
            elements = "YyYy",
            numberOfOperations = 2
        )
        assertThat(actual).isEqualTo(expected)
    }
}