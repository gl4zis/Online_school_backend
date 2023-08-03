package ru.spring.school.online.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.spring.school.online.model.security.User;
import ru.spring.school.online.repository.UserRepository;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    Further change requestMatchers(AntPathRequestMatcher.antMatcher("/register/**")).hasRole("ADMIN")
    Useless for now
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/register"),
                                AntPathRequestMatcher.antMatcher("/register/admin"),
                                AntPathRequestMatcher.antMatcher("/callback/"),
                                AntPathRequestMatcher.antMatcher("/webjars/**"),
                                AntPathRequestMatcher.antMatcher("/error**"),
                                AntPathRequestMatcher.antMatcher("/css/**"))
                        .permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/admin"),
                                AntPathRequestMatcher.antMatcher("/admin/**"))
                        .hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> {
                    form.loginPage("/login");
                    form.permitAll();
                    form.defaultSuccessUrl("/profile");
                })
                .getOrBuild();
    }
}
