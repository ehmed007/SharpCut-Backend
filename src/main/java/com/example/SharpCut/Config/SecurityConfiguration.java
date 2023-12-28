package com.example.SharpCut.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtFilterAuthentication jwtAuthenticationFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests()
//                .requestMatchers("admin/**").hasRole("ADMIN")
//                .requestMatchers("user/**").hasAnyRole("ADMIN","USER")
                .requestMatchers("auth/**").permitAll()
                .requestMatchers("barber/**").permitAll()
//                .requestMatchers("/api/v2/profile/**").authenticated()
//                .requestMatchers("/getToken").permitAll()
//                .requestMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authenticationProvider(this.authenticationProvider);
        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}




