package codeit.student.kotlintemplate.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BabylonControllerTest {
    @Test
    fun `test case 1`() {
        val expected = 5
        val actual = BabylonController.getNumberOfBooksRead(
            books = listOf(114, 111, 41, 62, 64),
            days = arrayListOf(157, 136, 130),
            bookIndex = 0,
            booksRead = 0
        )
        assertThat(actual).isEqualTo(expected)
    }
}