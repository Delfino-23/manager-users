package com.manager.clients.config;

import com.manager.clients.security.JwtAuthenticationEntryPoint;
import com.manager.clients.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

/**
 * A classe Security Configuration é responsável por configurar as regras de segurança
 * da aplicação, definindo quais endpoints são públicos e quais precisam de autenticação.
 */

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter, 
        JwtAuthenticationEntryPoint unauthorizedHandler) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.POST, "/administrators").permitAll()
                .requestMatchers(HttpMethod.POST, "/instructors").permitAll()

                // Apenas ADMIN pode listar todos os usuários
                .requestMatchers(HttpMethod.GET, "/users/").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/courses/").permitAll()
                .requestMatchers(HttpMethod.GET, "/courses/*").permitAll()

                // Apenas ADMIN e INTRUCTOR podem criar, atualizar e deletar cursos
                .requestMatchers(HttpMethod.POST, "/courses").hasAnyRole("ADMIN", "INSTRUCTOR")
                .requestMatchers(HttpMethod.PUT, "/courses/**").hasAnyRole("ADMIN", "INSTRUCTOR")
                .requestMatchers(HttpMethod.DELETE, "/courses/**").hasAnyRole("ADMIN", "INSTRUCTOR")
                .anyRequest().authenticated()
            );

        http.addFilterBefore(jwtAuthenticationFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Este bean será usado para codificar as senhas dos usuários.
     * @return PasswordEncoder configurado com BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Este bean expõe o AuthenticationManager para ser usado na autenticação.
     * @param authConfig
     * @return AuthenticationManager
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
