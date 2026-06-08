package com.minimarket.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/api/productos/**").authenticated()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.permitAll());

        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
        
        return http.build();
    }

    // Configuración obligatoria para vincular LDAP con Spring Security
    @Bean
    public BaseLdapPathContextSource contextSource() {
        DefaultSpringSecurityContextSource contextSource = 
            new DefaultSpringSecurityContextSource("ldap://localhost:389/dc=minimarket,dc=com");
        contextSource.setUserDn("cn=admin,dc=minimarket,dc=com");
        contextSource.setPassword("tu_contraseña_admin"); // Asegúrate que coincida con tu .properties
        contextSource.afterPropertiesSet();
        return contextSource;
    }

    // Método que registra el proveedor de LDAP en la seguridad global
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .ldapAuthentication()
            .contextSource(contextSource())
            .userDnPatterns("uid={0},ou=users"); // Ajusta el 'ou' según tu estructura LDAP real
    }
}