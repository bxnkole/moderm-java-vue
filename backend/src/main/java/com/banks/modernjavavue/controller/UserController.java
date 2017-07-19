package com.banks.modernjavavue.controller;

import com.banks.modernjavavue.model.UserEntity;
import com.banks.modernjavavue.service.UserService;
import org.springframework.http.MediaType;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserEntity> getAllUser() {
        return userService.getAll();
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String login(HttpServletRequest request) {
        return "";
    }

    @PostMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String logout(HttpServletRequest request) {
        return "";
    }
}
