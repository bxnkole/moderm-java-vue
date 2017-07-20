package com.banks.modernjavavue.config;

import com.banks.modernjavavue.filter.CorsFilter;
import com.banks.modernjavavue.filter.JWTAuthenticationFilter;
import com.banks.modernjavavue.filter.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by banks on 019 Jul 19 2017 9:39 PM.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.

                csrf().disable().

                authorizeRequests().
                antMatchers("/").permitAll().
                antMatchers("/favicon.ico").permitAll().
                antMatchers(HttpMethod.POST, "/api/user/login").permitAll().
                antMatchers(HttpMethod.POST, "/login").permitAll().
                anyRequest().fullyAuthenticated()

                .and().

                addFilterBefore(new JWTLoginFilter("/api/user/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class).
                addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class).
                addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


}
