package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.xerox.Document
import codeit.student.kotlintemplate.models.xerox.PrintJob
import codeit.student.kotlintemplate.models.xerox.XeroxRequest
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedReader
import javax.servlet.http.HttpServletRequest

@RestController
class XeroxController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/")
    fun getOptimalPrintSchedule(request: HttpServletRequest): Map<String, List<PrintJob>> {

        logger.info("Request received $request")
        print(request.inputStream.bufferedReader().use(BufferedReader::readText))
//        val response = evaluate(request.num_of_a3_copiers, request.num_of_a4_copiers, request.documents)
//        logger.info("Returning result $response")
//        return response
        return mapOf()
    }

    companion object {
        fun evaluate(num_of_a3_copiers: Int, num_of_a4_copiers: Int, documents: List<Map<String, Document>>): Map<String, List<PrintJob>> {
            return documents.map {
                val id       = it.keys.first()
                val document = it.values.first()
                val numOfCopiers   = if (document.page_format == "A3") num_of_a3_copiers else num_of_a4_copiers
                val copierPrefix   = if (document.page_format == "A3") "M" else "N"
                val pagesPerCopier = document.num_of_pages / numOfCopiers
                val remainder      = document.num_of_pages % numOfCopiers
                var fromPage       = 1

                val printJobs = (0 until numOfCopiers).map { i ->
                    val pagesToPrint = pagesPerCopier + if (i < remainder) 1 else 0
                    val from = fromPage
                    fromPage += pagesToPrint

                    PrintJob(
                        from = from,
                        to = from + pagesToPrint - 1,
                        copier = "$copierPrefix$i"
                    )
                }


                id to printJobs
            }.toMap()
        }
    }
}