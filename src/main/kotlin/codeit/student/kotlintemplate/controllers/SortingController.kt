package codeit.student.kotlintemplate.controllers

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SortingController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/sort")
    fun sort(@RequestBody numbers: List<Int>):  List<Int> {
        return if (numbers.size < 10000) {
            numbers.sorted()
        } else {
            val arr = MutableList(20001) { 0 }
            for (num in numbers) {
                arr[num+10000] += 1
            }

            val sorted = MutableList(numbers.size) { 0 }
            var index = 0
            for (i in 0..20000) {
                repeat(arr[i]) { sorted[index++] = i-10000}
            }
            sorted
        }
    }
}