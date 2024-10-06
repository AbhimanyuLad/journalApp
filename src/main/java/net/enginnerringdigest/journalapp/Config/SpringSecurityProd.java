package net.enginnerringdigest.journalapp.Config;

import org.springframework.security.config.Customizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import net.enginnerringdigest.journalapp.service.CustomUserDetailsMpl;


@Configuration
@EnableWebSecurity
@Profile("prod")
public class SpringSecurityProd {

    @Autowired
    private CustomUserDetailsMpl customUserDetailsMpl;
    
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                    .requestMatchers("/user/**", "/journal/**").authenticated() 
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll()  // Public routes

                            
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
        }
    
    
    public void passwordDecoder(AuthenticationManagerBuilder passs) throws Exception {
        passs.userDetailsService(customUserDetailsMpl).passwordEncoder(passwordEncoder());
    }
  


    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


