package com.banks.modernjavavue.service;

import com.banks.modernjavavue.model.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by banks on 019 Jul 19 2017 10:21 PM.
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public AppUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userByUsername = userService.getUserByUsername(username);
        if (userByUsername == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String password = userByUsername.getPassword();
        return new User(username, password, Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
    }
}
