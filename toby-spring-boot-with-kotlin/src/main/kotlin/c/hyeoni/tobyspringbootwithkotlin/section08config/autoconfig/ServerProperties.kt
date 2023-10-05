package c.hyeoni.tobyspringbootwithkotlin.section08config.autoconfig

import c.hyeoni.tobyspringbootwithkotlin.section08config.MyConfigurationProperties
import org.springframework.stereotype.Component

@MyConfigurationProperties(prefix = "server")
data class ServerProperties(
    val contextPath: String = "",
    val port: Int = 8080
)
