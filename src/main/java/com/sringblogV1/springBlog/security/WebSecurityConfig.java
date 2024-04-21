package com.sringblogv1.springblog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @SuppressWarnings("removal")
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(auth -> {
          try {
            // auth.requestMatchers("/", "/assets/**", "/css/**", "/js/**", "/about",
            // "/contact", "/blogs","/blog-post/**", "/admin/assets/**", "/admin/css/**",
            // "/admin/js/**", "/404", "/blog-post")
            auth.requestMatchers("/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(login -> login
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/admin", true)
                    .failureUrl("/login?error")
                    .permitAll())
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/logout?success"))
                .httpBasic(withDefaults());
          } catch (Exception e) {
            e.printStackTrace();
          }

          /*
           * auth.requestMatchers("/assets/**").permitAll();
           * auth.requestMatchers("/css/**").permitAll();
           * auth.requestMatchers("/js/**").permitAll();
           * auth.requestMatchers("/about").permitAll();
           * auth.requestMatchers("/contact").permitAll();
           * auth.requestMatchers("/blogs").permitAll();
           * auth.anyRequest().authenticated();
           */
        }).formLogin(withDefaults())
        .build();
  }

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @SuppressWarnings("removal")
  @Bean
  @Order(1)
  SecurityFilterChain h2SecurityFilterChain(HttpSecurity http) throws Exception {
    return http
        .securityMatcher(AntPathRequestMatcher.antMatcher("/db-console/**"))
        .authorizeHttpRequests(auth -> {
          auth.requestMatchers(AntPathRequestMatcher.antMatcher("/db-console/**")).permitAll();

        })
        .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/db-console/**")))
        .headers(headers -> headers.frameOptions().disable())
        .build();
  }

}
