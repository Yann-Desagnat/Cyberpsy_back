package com.cyberpsy.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebSecurityConfig {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    // Configurer l'authentification de l'utilisateur
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // Lier ton service personnalisé d'utilisateur
        authProvider.setPasswordEncoder(passwordEncoder()); // Encoder les mots de passe
        return authProvider;
    }

    // Filtre pour les JWT (authentification via token)
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    // Gestionnaire d'authentification
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Encoder le mot de passe avec BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuration de la sécurité HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Désactiver CSRF
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Utilisation de la configuration CORS définie ci-dessous
            .exceptionHandling(exceptionHandling ->
                    exceptionHandling.authenticationEntryPoint(unauthorizedHandler) // Gestion des erreurs d'authentification
            )
            .sessionManagement(sessionManagement ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Séance sans état (stateless)
            )
            .authorizeHttpRequests(authorizeRequests ->
                    authorizeRequests
                        .requestMatchers("/api/auth/**", "/api/qcm/submit/**", "/api/test/all","/api/qcm/niveau/**").permitAll() // Permet d'accéder à ces requêtes sans authentification
                        .anyRequest().authenticated() /// Authentification nécessaire pour les autres requêtes
     
            )
            .authenticationProvider(authenticationProvider()); // Appliquer l'authentification

        // Ajouter le filtre JWT avant le filtre d'authentification classique
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Configuration CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Autoriser le frontend React sur localhost:3000
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Méthodes autorisées
        configuration.setAllowedHeaders(List.of("*")); // Autoriser tous les en-têtes
        configuration.setAllowCredentials(true); // Autoriser les cookies et l'authentification

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Appliquer la configuration CORS à toutes les routes
        return source;
    }
}
