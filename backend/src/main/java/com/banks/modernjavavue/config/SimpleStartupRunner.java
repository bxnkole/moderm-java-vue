package com.banks.modernjavavue.config;

import com.banks.modernjavavue.model.UserEntity;
import com.banks.modernjavavue.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created on 2016-05-25.
 *
 * @author lavenderx
 */
@Order(0)
@Component
@Slf4j
public class SimpleStartupRunner implements CommandLineRunner {

    private final UserService userService;

    public SimpleStartupRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(">>>>>>>>>>>>>>>> Spring Boot extra startup is running <<<<<<<<<<<<<<<<");

        String username = "bxnkole";
        UserEntity byUsername = userService.getUserByUsername(username);

        if (byUsername == null) {
            byUsername = new UserEntity();
            byUsername.setUsername(username);
            byUsername.setPassword(new BCryptPasswordEncoder().encode("password"));
            byUsername.setEmail("bhsalako@gmail.com");
            byUsername.setState(true);
            byUsername.setBirthday(new Date());

            userService.save(byUsername);
        }
    }
}
