package com.banks.modernjavavue.controller;

import com.banks.modernjavavue.model.QuoteEntity;
import com.banks.modernjavavue.model.UserEntity;
import com.banks.modernjavavue.service.QuoteService;
import com.banks.modernjavavue.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2016-01-18.
 *
 * @author lavenderx
 */
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    private UserService userService;
    private QuoteService quoteService;

    public UserController(UserService userService, QuoteService quoteService) {
        this.userService = userService;
        this.quoteService = quoteService;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserEntity> getAllUser() {
        return userService.getAll();
    }

    @GetMapping(path = "/secretQuote", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String secretQuote() {
        QuoteEntity randomQuote = quoteService.getRandomQuote();
        return randomQuote.getQuote();
    }

    @PostMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String logout(HttpServletRequest request) {
        return "";
    }
}
