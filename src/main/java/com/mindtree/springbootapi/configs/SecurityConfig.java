package com.mindtree.springbootapi.configs;

import com.mindtree.springbootapi.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/sample/api/users")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/sample/api/users")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/sample/api/**")
                .hasAnyRole("ADMIN","ADMINTRAINEE")
                .antMatchers(HttpMethod.GET,"/sample/api/**")
                .hasAnyRole("USER","ADMIN","ADMINTRAINEE")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
