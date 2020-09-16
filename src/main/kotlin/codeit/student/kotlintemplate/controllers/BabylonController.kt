package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.babylon.BabylonRequest
import codeit.student.kotlintemplate.models.babylon.BabylonResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BabylonController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/olympiad-of-babylon")
    fun getOptimalNumberOfBooks(@RequestBody request: BabylonRequest): BabylonResponse {
        logger.info("Request received $request")
        val response = BabylonResponse(
            getNumberOfBooksRead(
                books = request.books,
                days  = request.days,
                bookIndex = 0,
                booksRead = 0
            )
        )
        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun getNumberOfBooksRead(books: List<Int>, days: ArrayList<Int>, bookIndex: Int, booksRead: Int): Int {
            if (bookIndex >= books.size)  return booksRead

            val book = books[bookIndex]
            var max = booksRead
            for (i in 0 until days.size) {
                if (book <= days[i]) {
                    days[i] -= book
                    val booksReadIfChosen = getNumberOfBooksRead(books, days, bookIndex + 1, booksRead + 1)
                    if (booksReadIfChosen > max) {
                        max = booksReadIfChosen
                    }
                    days[i] += book
                }
            }

            val booksReadIfNotChosen = getNumberOfBooksRead(books, days, bookIndex + 1, booksRead)
            if (booksReadIfNotChosen > max) {
                max = booksReadIfNotChosen
            }

            return max
        }
    }
}