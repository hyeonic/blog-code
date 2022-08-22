package io.github.hyeonic.mappedsuperclassvsembeddable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MappedSuperclassVsEmbeddableApplication {

    public static void main(String[] args) {
        SpringApplication.run(MappedSuperclassVsEmbeddableApplication.class, args);
    }

}
