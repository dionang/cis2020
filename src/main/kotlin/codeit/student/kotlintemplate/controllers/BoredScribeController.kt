package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.scribe.Answer
import codeit.student.kotlintemplate.models.scribe.Test
import org.slf4j.LoggerFactory
import org.springframework.core.io.ResourceLoader
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.ln

@RestController
class BoredScribeController(val resourceLoader: ResourceLoader) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/bored-scribe")
    fun evaluate(@RequestBody request: List<Test>): List<Answer> {
        logger.info("Request received $request")
        val response = request.map {
            val decryptedText = decrypt(it.encryptedText)
            Answer(
                id = it.id,
                encryptionCount = getEncryptionCount(decryptedText, it.encryptedText),
                originalText = getExpandedString(decryptedText)
            )
        }
        logger.info("Returning result $response")
        return response
    }

    fun getEncryptionCount(decryptedText: String, encryptedText: String): Int {
        return 1
    }

    fun getExpandedString(string: String): String {
        val reader = BufferedReader(
            InputStreamReader(resourceLoader.getResource("classpath:wordlist.txt").inputStream)
        )

        val dictionary = mutableSetOf<String>()
        while (reader.ready()) {
            dictionary.add(reader.readLine())
        }

        val words = findValidSolutionIfExists(string, dictionary, string.length)

        return string.substring(0, string.length - words.sumBy{ it.length }) + words.joinToString(separator = " ")
    }

    fun findValidSolutionIfExists(string: String, dictionary: Set<String>, toIndex: Int): List<String> {
        if (toIndex <= 0) return arrayListOf()

        var token = ""
        var prevToken = ""
        var nextWord = ""
        var currWords = emptyList<String>()
        for (i in (toIndex-1) downTo 0) {
            token = string[i] + token
            if (token in dictionary) {
                val nextWords = findValidSolutionIfExists(string, dictionary, toIndex-token.length)
                val isValidSolution = nextWords.firstOrNull() ?.let { it in dictionary } ?: false
                if (isValidSolution && token.length > prevToken.length) {
                    nextWord = token
                    currWords = nextWords
                }

                prevToken = token
            }
        }

        if (nextWord.isEmpty()) return listOf(string.substring(0, toIndex))
        return currWords + nextWord
    }

    fun findNextWordsGreedy(string: String, dictionary: Set<String>, toIndex: Int): List<String> {
        if (toIndex < 0) return arrayListOf()

        var token = ""
        var prevToken = ""
        var nextWord = ""
        var currWords = emptyList<String>()
        for (i in (toIndex-1) downTo 0){
            token = string[i] + token
            if (token in dictionary) {
                if (token.length > prevToken.length) {
                    val nextWords = findNextWordsGreedy(string, dictionary, toIndex-token.length)
                    if (nextWords.size >= currWords.size ) {
                        nextWord = token
                        currWords = nextWords
                    }
                }
                prevToken = token
            }
        }

        if (nextWord.isEmpty()) return emptyList()
        return currWords + nextWord
    }

    fun decrypt(encryptedText: String): String {
        return (1..26).map { shift ->
            val decryptedText = decode(encryptedText, shift)
            decryptedText to getEntropy(decryptedText)
        }.minBy { it.second }!!.first
    }

    companion object {
        private val ENGLISH_FREQS = listOf(
            0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.00153, 0.00772, 0.04025, 0.02406,
            0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 0.02758, 0.00978, 0.02360, 0.00150, 0.01974, 0.00074
        )

        private fun getEntropy(string: String): Double {
            var sum = 0.0
            for (ch in string) {
                sum += ln(ENGLISH_FREQS[ch.toInt() - 97])
            }
            return -sum / ln(2.0) / string.length
        }

        private fun decode(encryptedText: String, shift: Int): String {
            return encryptedText.map { ch ->
                (((ch.toInt() - 97 + shift) % 26) + 97).toChar()
            }.joinToString(separator = "")
        }
    }
}