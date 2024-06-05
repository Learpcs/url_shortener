package com.url_shortener.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers(new RegexRequestMatcher("/[a-zA-Z0-9]{5}", null)).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/signin")
//                        .permitAll()
//                )
//                .logout(LogoutConfigurer::permitAll);
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(new RegexRequestMatcher("/[a-zA-Z0-9]{5}", null)).permitAll()
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/private").authenticated()
                        .requestMatchers("/admintest").hasRole("ADMIN")
                        .requestMatchers("/swagger-ui/*").permitAll()
                        .anyRequest().permitAll()
                )
//                .formLogin((form) -> form
//                        .loginPage("/signin")
//                        .permitAll()
//                )
                .exceptionHandling(authorise -> authorise.accessDeniedPage("/access-denied"))
                .logout(LogoutConfigurer::permitAll)
                .formLogin(withDefaults());
//                .oauth2Login(withDefaults());
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .roles("user")
                        .build();

        UserDetails admin =
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles("user", "admin")
                        .build();


        return new InMemoryUserDetailsManager(user, admin);
    }

//    @Bean
//    public AuthenticationManager configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        return auth.build();
//    }
}