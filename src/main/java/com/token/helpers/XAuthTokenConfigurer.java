package com.token.helpers;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class XAuthTokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private UserDetailsService userDetailsService;

    public XAuthTokenConfigurer(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        XAuthTokenFilter customFilter = new XAuthTokenFilter(userDetailsService);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
