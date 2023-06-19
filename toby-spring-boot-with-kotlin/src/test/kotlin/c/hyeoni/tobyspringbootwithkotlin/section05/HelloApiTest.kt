package c.hyeoni.tobyspringbootwithkotlin.section05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class HelloApiTest {

    @Test
    fun helloApi() {
        val restTemplate = TestRestTemplate()

        val response = restTemplate.getForEntity(
            "http://localhost:8080/hello?name={name}",
            String::class.java,
            "hyeonic"
        )

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.headers.getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE)
        assertThat(response.body).contains("Hello hyeonic")
    }

    @Test
    fun failHelloApi() {
        val restTemplate = TestRestTemplate()

        val response = restTemplate.getForEntity(
            "http://localhost:8080/hello?name=",
            String::class.java
        )

        assertThat(response.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
