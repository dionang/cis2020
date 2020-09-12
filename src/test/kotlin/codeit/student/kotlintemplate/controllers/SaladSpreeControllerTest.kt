package codeit.student.kotlintemplate.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SaladSpreeControllerTest {
    @Test
    fun `test case 1`() {
        val expected = 24
        val actual = SaladSpreeController.getMinAmountSpent(
            numberOfSalads = 3,
            saladPricesStreetMap = listOf(
                listOf("12", "12", "3", "X", "3"),
                listOf("23", "X", "X", "X", "3"),
                listOf("33", "21", "X", "X", "X"),
                listOf("9", "12", "3", "X", "X"),
                listOf("X", "X", "X", "4", "5")
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 2`() {
        val expected = 0
        val actual = SaladSpreeController.getMinAmountSpent(
            numberOfSalads = 3,
            saladPricesStreetMap = listOf(
                listOf("X", "X", "2"),
                listOf("2", "3", "X"),
                listOf("X", "3", "2")
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `test case 3`() {
        val expected = 5
        val actual = SaladSpreeController.getMinAmountSpent(
            numberOfSalads = 2,
            saladPricesStreetMap = listOf(
                listOf("2", "3", "X", "2"),
                listOf("4", "X", "X", "4"),
                listOf("3", "2", "X", "X"),
                listOf("X", "X", "X", "5")
            )
        )

        assertThat(actual).isEqualTo(expected)
    }
}