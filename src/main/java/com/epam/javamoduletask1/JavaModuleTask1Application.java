package com.epam.javamoduletask1;

import com.epam.javamoduletask1.entity.User;
import com.epam.javamoduletask1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class JavaModuleTask1Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaModuleTask1Application.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository) {
        return (String[] args) -> {
            User user1 = new User("Muzaffar", "muzaffar@gmail.com");
            User user2 = new User("Umar", "umar@gmail.com");
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.findAll().forEach(System.out::println);
        };
    }

}
