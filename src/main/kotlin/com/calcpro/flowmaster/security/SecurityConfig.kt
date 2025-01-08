//package com.calcpro.flowmaster.security
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.web.SecurityFilterChain
//
//@Configuration
//@EnableWebSecurity
//class SecurityConfig {
//
//    @Bean
//    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
//        httpSecurity.formLogin {
//            it.loginPage("/login").permitAll()
//        }
//            .authorizeHttpRequests {
//                it.regexMatchers("reg/signup").permitAll()
//                it.anyRequest().authenticated()
//            }
//
//        return httpSecurity.build()
//    }
//}