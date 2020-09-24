package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.xerox.Document
import codeit.student.kotlintemplate.models.xerox.PrintJob
import codeit.student.kotlintemplate.models.xerox.XeroxRequest
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class XeroxController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/xerox")
    fun getOptimalPrintSchedule(@RequestBody request: XeroxRequest): Map<String, List<PrintJob>> {
        logger.info("Request received $request")
        val response = evaluate(request.num_of_a3_copiers, request.num_of_a4_copiers, request.documents)
        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun evaluate(num_of_a3_copiers: Int, num_of_a4_copiers: Int, documents: Map<String, Document>): Map<String, List<PrintJob>> {
            return documents.map { (id, document) ->
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