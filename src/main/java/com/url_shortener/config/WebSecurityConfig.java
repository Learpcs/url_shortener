package com.url_shortener.config;

import org.keycloak.adapters.springsecurity.authentication.KeycloakLogoutHandler;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.*;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
public class WebSecurityConfig {


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
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(new RegexRequestMatcher("/[a-zA-Z0-9]{5}", null)).permitAll()
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/private").authenticated()
                        .requestMatchers("/admintest").hasRole("admin")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(authorise -> authorise.accessDeniedPage("/access-denied"))
                .logout(LogoutConfigurer::permitAll)
                .formLogin(withDefaults());
//                .oauth2Login(withDefaults());
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.builder()
//                        .username("user")
//                        .password(passwordEncoder().encode("user"))
//                        .roles("user")
//                        .build();
//
//        UserDetails admin =
//                User.builder()
//                        .username("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .roles("user", "admin")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

//    @Bean
//    @SuppressWarnings("unchecked")
//    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
//
//        return (authorities) -> {
//            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
//            authorities.forEach(authority -> {
//                if (authority instanceof OidcUserAuthority oidcUserAuthority) {
//                    OidcUserInfo userInfo = oidcUserAuthority.getUserInfo();
//                    Map<String, Object> realmAccess = userInfo.getClaim("realm_access");
//                    Collection<String> realmRoles;
//                    if (realmAccess != null
//                            && (realmRoles = (Collection<String>) realmAccess.get("roles")) != null) {
//                        realmRoles
//                                .forEach(role -> mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
//                    }
//                }
//            });
//            return mappedAuthorities;
//        };
//    }
//
//    @Bean
//    OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler(ClientRegistrationRepository clientRegistrationRepository) {
//        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
//        successHandler.setPostLogoutRedirectUri(URI.create("http://localhost:8888").toString());
//        return successHandler;
//    }
//
//    @Bean
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }
}