package com.example.variouscontainers.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/sign").permitAll();
                    authorize.requestMatchers("/oauth2/**").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(oauth ->
                        oauth.loginPage("/sign")
                );

        return http.build();

    }

}
