package com.codeclan.example.paintzServer.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserPrincipalDetailsService userPrincipalDetailsService;

    // this one defines data source for the users
    // authorities allow you to get granular with permissions within roles (roles are less flexible but great for simple apps)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(myAuthenticationProvider());

//                .inMemoryAuthentication()
//                .withUser("admin@test.com")
//                    .password(passwordEncoder().encode("admin"))
//                    .roles("ADMIN").authorities("ACCESS_ADMIN", "ROLE_ADMIN")
//                .and()
//                .withUser("heather@test.com")
//                    .password(passwordEncoder().encode("password"))
//                    .roles("USER");
    }



    // this one authorises requests
    // order of the antMatchers is important - executed in order
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .anyRequest().authenticated() // or .permitAll()
                .antMatchers("/api/paints").permitAll()
//                .antMatchers("/user/**").authenticated()
//                .antMatchers("/admin").hasRole("ADMIN")  // can comma separate multiple roles
                .antMatchers("/api/users").hasRole("USER")
                .antMatchers("/api/admin").hasAuthority("ACCESS_ADMIN")
                .antMatchers("/api/users").hasRole("ADMIN")
                .and()
                .httpBasic();
    }

    // setting up an authentication provider, rather than using in-memory authentication
    @Bean
    DaoAuthenticationProvider myAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    // as of Spring Boot 2, must include password encoder and use it to encode passwords
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}