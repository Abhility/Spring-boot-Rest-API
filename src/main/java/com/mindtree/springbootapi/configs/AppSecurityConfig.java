package com.mindtree.springbootapi.configs;

import com.mindtree.springbootapi.jwt.JwtConfig;
import com.mindtree.springbootapi.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.mindtree.springbootapi.jwt.JwtValidator;
import com.mindtree.springbootapi.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtConfig jwtConfig;

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
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig))
                .addFilterAfter(new JwtValidator(jwtConfig),JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/sample/api/users")
                .permitAll()
                .antMatchers("/")
                .permitAll()
                /*.antMatchers(HttpMethod.GET,"/sample/api/users")
                .hasAnyRole("ADMIN","ADMINTRAINEE")
                .hasAnyAuthority(USER_READ.getAuthority())
                .antMatchers(HttpMethod.POST,"/sample/api/**")
                .hasAnyRole("ADMIN","ADMINTRAINEE")
                .hasAnyAuthority(TOPIC_WRITE.getAuthority(),
                                 TOPIC_UPDATE.getAuthority(),
                                 CHAPTER_WRITE.getAuthority(),
                                 CHAPTER_UPDATE.getAuthority())
                .antMatchers(HttpMethod.GET,"/sample/api/**")
                .hasAnyRole("USER","ADMIN","ADMINTRAINEE")
                .hasAnyAuthority(TOPIC_READ.getAuthority(),
                                 CHAPTER_READ.getAuthority())*/
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
