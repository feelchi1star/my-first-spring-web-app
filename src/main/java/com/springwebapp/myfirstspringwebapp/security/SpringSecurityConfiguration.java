package com.springwebapp.myfirstspringwebapp.security;

import java.util.function.Function;
// import static 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    // LDAP or Database
    // IN memory

    @Bean
    InMemoryUserDetailsManager createDetailsManager() {
        String username = "spring";
        String password = "1234";
        UserDetails userDetails1 = createNewUser(username, password);
        UserDetails userDetails2 = createNewUser("Feel", "1234");
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String password) {
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
        UserDetails userDetails = User.builder().username(username).passwordEncoder(passwordEncoder).password(password)
                .roles("USER", "ADMIN").build();
        return userDetails;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // All URLS are protected
    // A login is shown for unauthorized requests
    // CSRF disable
    // Frames

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()); // authorize
        http.formLogin(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.headers(header -> header.frameOptions(frameOption -> frameOption.disable()));
        return http.build();
    }

}
