package codeit.student.kotlintemplate

import codeit.student.kotlintemplate.models.SquareCalcRequest
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class SquareAcceptanceTests {
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun shouldReturnSquare() {
        val request = SquareCalcRequest(input = 2)
        val response = testRestTemplate.postForEntity("/square", HttpEntity(request), Int::class.java)
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        val result = response.body
        Assertions.assertThat(result).isNotNull()
        Assertions.assertThat(result).isEqualTo(4)
    }
}