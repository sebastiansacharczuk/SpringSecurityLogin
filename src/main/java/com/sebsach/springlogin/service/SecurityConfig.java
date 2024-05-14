package com.sebsach.springlogin.service;

//import com.sebsach.springlogin.jwt.AuthEntryPointJwt;
//import com.sebsach.springlogin.jwt.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // USAGE:@PreAuthorize(hasRole('ROLE_NAME'))
@RequiredArgsConstructor
public class SecurityConfig{


//    @Autowired
//    DataSource dataSource;
//
//    @Autowired
//    private AuthEntryPointJwt unauthorizedHandler;
//
//
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
//
//    @Bean
//    public AuthTokenFilter authenticateJwtTokenFilter() {
//        return new AuthTokenFilter();
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(auth -> auth
                        .requestMatchers("/login", "register").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
//        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.addFilterBefore(authenticateJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 = User.withUsername("user1")
//                .password(passwordEncoder().encode("userPass1"))
//                .roles("USER")
//                .build();
//        UserDetails admin1 = User.withUsername("admin1")
//                .password(passwordEncoder().encode("adminPass1"))
//                .roles("ADMIN")
//                .build();
////        return new InMemoryUserDetailsManager(user1,admin1);
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
////        userDetailsManager.createUser(user1);
////        userDetailsManager.createUser(admin1);
//        return userDetailsManager;
//    }
}
