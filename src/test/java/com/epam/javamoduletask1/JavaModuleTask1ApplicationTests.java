package com.epam.javamoduletask1;

import com.epam.javamoduletask1.entity.User;
import com.epam.javamoduletask1.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaModuleTask1Application.class)
@DataJpaTest
class JavaModuleTask1ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenSaveUser() {
        User user = new User("Muzaffar", "muzaffar@gmail.com");
        userRepository.save(user);
        List<User> users = (List<User>) userRepository.findAll();
        assertThat(users.size()).isEqualTo(1);

    }
}
