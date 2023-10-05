package c.hyeoni.tobyspringbootwithkotlin.section08config.autoconfig

import c.hyeoni.tobyspringbootwithkotlin.section08config.MyAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.DispatcherServlet

@MyAutoConfiguration
class DispatcherServletConfig {

    @Bean
    fun dispatcherServlet(): DispatcherServlet {
        return DispatcherServlet()
    }
}
