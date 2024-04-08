package com.sringblogv1.springblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    return http
              .authorizeHttpRequests(auth->{
                auth.requestMatchers("/","/assets/**","/css/**","/js/**","/about","/contact","/blogs", "/blog-post").permitAll();
                /*
                auth.requestMatchers("/assets/**").permitAll();
                auth.requestMatchers("/css/**").permitAll();
                auth.requestMatchers("/js/**").permitAll();
                auth.requestMatchers("/about").permitAll();
                auth.requestMatchers("/contact").permitAll();
                auth.requestMatchers("/blogs").permitAll();
                auth.anyRequest().authenticated(); */
              }).formLogin(withDefaults())
              .build();
  }
    @SuppressWarnings("removal")
    @Bean
    @Order(1)
    SecurityFilterChain h2SecurityFilterChain(HttpSecurity http) throws Exception{
      return http
                .securityMatcher(AntPathRequestMatcher.antMatcher("/db-console/**"))
                .authorizeHttpRequests(auth->{
                  auth.requestMatchers(AntPathRequestMatcher.antMatcher("/db-console/**")).permitAll();

                })
                .csrf(csrf->csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/db-console/**")))
                .headers(headers->headers.frameOptions().disable())
                .build();
    }
  
}
