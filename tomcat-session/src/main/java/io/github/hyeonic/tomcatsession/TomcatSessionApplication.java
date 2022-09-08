package io.github.hyeonic.tomcatsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class TomcatSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomcatSessionApplication.class, args);
    }
}
