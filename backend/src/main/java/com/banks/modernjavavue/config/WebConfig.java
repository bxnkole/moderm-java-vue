package com.banks.modernjavavue.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created on 2016-01-19.
 *
 * @author lavenderx
 */
@Order(0)
@Configuration
@ComponentScan(basePackages = "com.banks.modernjavavue.controller",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.stereotype.Controller.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.web.bind.annotation.RestController.class)
        },
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.stereotype.Service.class)
)
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);

        return jackson2HttpMessageConverter;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding(StandardCharsets.UTF_8.name());
        multipartResolver.setMaxUploadSize(104857600);
        multipartResolver.setMaxInMemorySize(4096);

        return multipartResolver;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.addAll(Lists.newArrayList(stringHttpMessageConverter(), jackson2HttpMessageConverter()));
    }

    // Support Cross-site HTTP request
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH")
                .allowedHeaders("X-Requested-With", "Content-Type", "Accept", "Authorization")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
