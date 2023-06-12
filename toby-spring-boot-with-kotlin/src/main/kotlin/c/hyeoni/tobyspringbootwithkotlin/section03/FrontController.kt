package c.hyeoni.tobyspringbootwithkotlin.section03

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object FrontController {

    fun start() {
        val servletWebServerFactory = TomcatServletWebServerFactory()
        val webServer = servletWebServerFactory.getWebServer({ servletContext ->
            val helloController = HelloController()
            servletContext.addServlet(
                "hello",
                object : HttpServlet() {
                    override fun service(req: HttpServletRequest?, res: HttpServletResponse?) {
                        when {
                            req?.requestURI == "/hello" && req.method == HttpMethod.GET.name -> {
                                val name = req.getParameter("name")
                                res?.status = HttpStatus.OK.value()
                                res?.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                                res?.writer?.println(helloController.hello(name))
                            }
                            else -> res?.status = HttpStatus.NOT_FOUND.value()
                        }
                    }
                }
            ).addMapping("/*")
        })
        webServer.start()
    }
}
