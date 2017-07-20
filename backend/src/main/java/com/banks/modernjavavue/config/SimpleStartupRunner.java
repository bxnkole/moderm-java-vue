package com.banks.modernjavavue.config;

import com.banks.modernjavavue.model.QuoteEntity;
import com.banks.modernjavavue.model.UserEntity;
import com.banks.modernjavavue.repos.QuoteRepo;
import com.banks.modernjavavue.service.QuoteService;
import com.banks.modernjavavue.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private QuoteService quoteService;

    public SimpleStartupRunner(UserService userService, QuoteService quoteService) {
        this.userService = userService;
        this.quoteService = quoteService;
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

        if(quoteService.getTotalCount() == 0) {
            List<String> strings = IOUtils.readLines(SimpleStartupRunner.class.getResourceAsStream("/init-quote.txt"));
            List<QuoteEntity> collect = strings.stream().map(quote -> new QuoteEntity(quote)).collect(Collectors.toList());
            quoteService.create(collect);
        }
    }
}
