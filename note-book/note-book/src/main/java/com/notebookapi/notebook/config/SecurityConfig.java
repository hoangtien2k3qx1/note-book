package com.notebookapi.notebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        // The builder will ensure the passwords are encoded before saving in memory
        UserDetails user = User.builder()
                .username("hoangtien2k3")
                .password("$2a$10$UuwExKhhi4wDhCLVkb/4AOhwp.7WLZFQeQYzcGbxpuXT7gy6tXKtS")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("$2a$10$7La9gfjUSNnKNFRU/zGpCunm/eAvk1fQ7tTuwAeR66U1KcVigg8bu2")
                .roles("USER", "ADMIN")
                .build();

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user);
        users.createUser(admin);
        return users;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
