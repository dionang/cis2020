package codeit.student.kotlintemplate.controllers

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SortingController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/sort")
    fun sort(@RequestBody request: List<Int>):  List<Int> {
//        logger.info("Request received $request")
//        val response = BabylonResponse(
//            evaluate(request.books, request.days)
//        )
//        logger.info("Returning result $response")
        return request.sorted()
    }
}