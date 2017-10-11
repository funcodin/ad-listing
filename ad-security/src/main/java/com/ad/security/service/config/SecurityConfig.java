package com.ad.security.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.ad.security.service.auth.AuthenticationFailureHandler;
import com.ad.security.service.auth.AuthenticationSuccessHandler;
import com.ad.security.service.auth.RestAuthenticationEntryPoint;
import com.ad.security.service.auth.TokenAuthenticationFilter;
import com.ad.security.service.impl.CustomUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Value("${app.user_cookie}")
    private String USER_COOKIE;

    @Bean
    public TokenAuthenticationFilter jwtAuthenticationTokenFilter() throws Exception {
        return new TokenAuthenticationFilter();
    }

    @Autowired
    CustomUserDetailsService jwtUserDetailsService;

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService);
    }

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
                .exceptionHandling().authenticationEntryPoint( restAuthenticationEntryPoint ).and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest()
                    .authenticated().and()
                .formLogin()
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler).and()
                .logout()
                .deleteCookies(TOKEN_COOKIE, USER_COOKIE)
                .logoutSuccessUrl("/login");
    }	
	
}
