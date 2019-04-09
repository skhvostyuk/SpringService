package ru.tsc.lectures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching

public class LecturesApplication {

    public static void main(String[] args) {
        SpringApplication.run(LecturesApplication.class, args);
    }

}
