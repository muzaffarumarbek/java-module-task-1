package com.epam.javamoduletask1;

import com.epam.javamoduletask1.entity.User;
import com.epam.javamoduletask1.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JavaModuleTask1Application.class)
@EnableJpaRepositories(basePackages = { "com.epam.javamoduletask1.repository" })
class JavaModuleTask1ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenSaveUser() {
        User user = new User("muzaffar.umar@gmail.com");
        userRepository.save(user);

    }
}
