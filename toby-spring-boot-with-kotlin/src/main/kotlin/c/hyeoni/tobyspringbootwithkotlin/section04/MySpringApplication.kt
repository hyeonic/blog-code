package c.hyeoni.tobyspringbootwithkotlin.section04

import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

class MySpringApplication {

    companion object {

        fun run(applicationClass: Class<*>, args: Array<String>) {
            object : AnnotationConfigWebApplicationContext() {

                @Suppress("ACCIDENTAL_OVERRIDE")
                override fun setClassLoader(classLoader: ClassLoader) {
                    this.classLoader = classLoader
                }

                override fun onRefresh() {
                    super.onRefresh()

                    val servletWebServerFactory = this.getBean(ServletWebServerFactory::class.java)
                    val dispatcherServlet = this.getBean(DispatcherServlet::class.java)

                    val webServer = servletWebServerFactory.getWebServer({ servletContext ->
                        servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                            .addMapping("/*")
                    })

                    webServer.start()
                }
            }.apply {
                this.register(applicationClass)
                this.refresh()
            }
        }
    }
}

