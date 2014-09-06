package com.token.config;

import com.token.helpers.XAuthTokenConfigurer;
import com.token.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebMvcSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        String[] restEndpointToSecure = { "home" };
        for( String endpoint : restEndpointToSecure ) {
            http.authorizeRequests().antMatchers("/" + endpoint + "/**").hasRole(CustomUserDetailsService.ROLE_USER);
        }

        SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> securityConfigurerAdapter = new XAuthTokenConfigurer(userDetailsServiceBean());
        http.apply(securityConfigurerAdapter);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new CustomUserDetailsService());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }
}
