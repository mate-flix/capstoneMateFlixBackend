package com.mteflix.capstonemateflixbackend.config;

import com.mteflix.capstonemateflixbackend.auth.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public UserDetailsService userDetailsService(AuthService authService){
        return (username)-> getUserByUsername(authService, username);
    }

    private static User getUserByUsername(AuthService authService, String username) {
        var user = authService.getUserBy(username);
        var authorities = user.getAuthorities();
        var userAuthorities =
                authorities.stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.name()))
                        .toList();
        return new User(username, user.getPassword(), userAuthorities);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
