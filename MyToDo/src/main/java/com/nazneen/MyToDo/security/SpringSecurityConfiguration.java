package com.nazneen.MyToDo.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {

        UserDetails userDetails1 = createUserDetails("Nazneen", "Pass123");
        UserDetails userDetails2 = createUserDetails("test", "test123");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createUserDetails(String username, String password) {
        Function<String, String> encoder = input -> passwordEncoder().encode(input);

        return User.builder()
                .passwordEncoder(encoder)
                .username(username)
                .password(password)
                .roles("USER")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
        );

        http.formLogin(withDefaults());


        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));


        return http.build();
    }

}
