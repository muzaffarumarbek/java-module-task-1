package com.epam.javamoduletask1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class JavaModuleTask1Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaModuleTask1Application.class, args);
    }

}
