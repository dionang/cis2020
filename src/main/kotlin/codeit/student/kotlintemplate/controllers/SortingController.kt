package codeit.student.kotlintemplate.controllers

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.io.DataInputStream
import java.io.EOFException
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
class SortingController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/sort")
    fun sort(request: HttpServletRequest):  List<Int> {
        val dis = DataInputStream(request.inputStream)
        println(dis.readByte()) // ignores '['
        try {
            while (true) {
                val byte = dis.readByte()
            }
        } catch (e: EOFException) {

        }

        return emptyList()
    }
}