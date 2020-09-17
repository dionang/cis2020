package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.babylon.BabylonRequest
import codeit.student.kotlintemplate.models.babylon.BabylonResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.Integer.max

@RestController
class BabylonController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/olympiad-of-babylon")
    fun getOptimalNumberOfBooks(@RequestBody request: BabylonRequest): BabylonResponse {
        logger.info("Request received $request")
        val response = BabylonResponse(
            evaluate(request.books, request.days)
        )
        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun evaluate(books: List<Int>, days: ArrayList<Int>): Int {
            val maxBooks = max(days.size * 4, books.size)
            println("maxBooks: $maxBooks")
            return getNumberOfBooksRead(
                books = books.sorted().subList(0, maxBooks),
                days  = days,
                bookIndex = 0,
                booksRead = 0
            )
        }

        fun getNumberOfBooksRead(books: List<Int>, days: ArrayList<Int>, bookIndex: Int, booksRead: Int): Int {
            if (bookIndex >= books.size)  return booksRead

            val book = books[bookIndex]
            var max = booksRead
            var canExpand = false

            for (i in 0 until days.size) {
                if (book <= days[i]) {
                    days[i] -= book
                    val booksReadIfChosen = getNumberOfBooksRead(books, days, bookIndex + 1, booksRead + 1)
                    if (booksReadIfChosen > max) {
                        max = booksReadIfChosen
                    }
                    days[i] += book
                    canExpand = true
                }
            }

            if (!canExpand) return max

            val booksReadIfNotChosen = getNumberOfBooksRead(books, days, bookIndex + 1, booksRead)
            if (booksReadIfNotChosen > max) {
                max = booksReadIfNotChosen
            }

            return max
        }
    }
}