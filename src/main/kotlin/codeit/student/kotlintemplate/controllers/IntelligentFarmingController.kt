package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.farming.IntelligentFarmingRequestResponse
import codeit.student.kotlintemplate.models.farming.Test
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException
import kotlin.math.min

@RestController
class IntelligentFarmingController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/intelligent-farming")
    fun getGeneSequence(@RequestBody request: IntelligentFarmingRequestResponse): IntelligentFarmingRequestResponse {
        logger.info("Request received $request")
        val response = request.copy(
            list = request.list.map {
                Test(it.id, getMaxDRIGeneSequence(it.geneSequence))
            }
        )
        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun getMaxDRIGeneSequence(geneSequence: String): String {
            val sortedGeneSequence = buildGeneSequence(getSequenceComponents(geneSequence))

            val freq = mutableMapOf(
                'A' to 0,
                'C' to 0,
                'G' to 0,
                'T' to 0
            )

            val freq2 = mutableMapOf(
                'A' to 0,
                'C' to 0,
                'G' to 0,
                'T' to 0
            )

            for (ch in geneSequence) {
                freq[ch] = freq.getValue(ch) + 1
            }

            for (ch in sortedGeneSequence) {
                freq2[ch] = freq2.getValue(ch) + 1
            }

            if (freq != freq2) {
                println(freq)
                println(freq2)
                throw RuntimeException("string char frequencies do not match")
            }

            return buildGeneSequence(getSequenceComponents(geneSequence))
        }

        fun getSequenceComponents(geneSequence: String): MutableMap<String, Int> {
            val freq = mutableMapOf(
                'A' to 0,
                'C' to 0,
                'G' to 0,
                'T' to 0
            )

            for (ch in geneSequence) {
                freq[ch] = freq.getValue(ch) + 1
            }

            var acgt = freq.values.min()!!
            var cc   = freq.getValue('C') - acgt   // calculate unused c

            if (cc % 2 != 0) {                          // adjustment to score more points
                acgt -= 1
                cc   += 1
            }

            cc /= 2                                     // divide to find number of CC tokens

            return mutableMapOf(
                "ACGT" to acgt,
                "CC"   to cc,
                "A" to freq.getValue('A') - acgt,
                "G" to freq.getValue('G') - acgt,
                "T" to freq.getValue('T') - acgt
            )
        }

        fun buildGeneSequence(components: MutableMap<String, Int>): String {
            val builder = StringBuilder()

            // build using AACGT
            val aacgt = min(components.getValue("ACGT"), components.getValue("A"))
            components["ACGT"] = components.getValue("ACGT") - aacgt
            components["A"] = components.getValue("A") - aacgt
            builder.append("AACGT".repeat(aacgt))

            // use up as many A tokens as possible without getting 3 consecutive
            var consecutiveA = 0
            while (components.getValue("A") != 0) {
                if (consecutiveA < 2) {
                    components["A"] = components.getValue("A") - 1
                    builder.append("A")
                    consecutiveA += 1
                } else {
                    components.keys
                        .firstOrNull { it != "A" && components.getValue(it) > 0 }
                        ?.let {
                            components[it] = components.getValue(it) - 1
                            builder.append(it)
                            consecutiveA = 0
                        }
                        ?: break
                }
            }

            // use all remaining tokens
            components.entries.forEach { (key, count) ->
                builder.append(key.repeat(count))
            }

            return builder.toString()
        }
    }
}
