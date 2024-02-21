//package com.task.restaurantmanagement.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    AuthenticationProvider authenticationProvider;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
//        security.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
//                    authorizationManagerRequestMatcherRegistry
//                            .requestMatchers("/login").permitAll()
//                            . requestMatchers("/admin","/admin/**").hasAuthority("ADMIN")
//                            .requestMatchers("/restaurant","/restaurant/**").hasAuthority("RESTAURANT")
//                            .anyRequest().permitAll();
//                })
//                .formLogin(httpSecurityFormLoginConfigurer -> {
//                    httpSecurityFormLoginConfigurer.loginPage("/login")
//                            .loginProcessingUrl("/doLogin")
//                            .usernameParameter("email")
//                            .passwordParameter("password");
//                })
//                .authenticationProvider(authenticationProvider);
//        return security.build();
//    }
//
//
//}
