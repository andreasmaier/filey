package com.token.helpers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XAuthTokenFilter extends GenericFilterBean{

    private UserDetailsService userDetailsService;
    private final String xAuthTokenHeaderName = "x-auth-token";
    private TokenUtils tokenUtils = new TokenUtils();

    public XAuthTokenFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            String authToken = httpServletRequest.getHeader(this.xAuthTokenHeaderName);

            if(StringUtils.hasText(authToken)) {
                String username = this.tokenUtils.getUserNameFromToken(authToken);

                UserDetails details = this.userDetailsService.loadUserByUsername(username);

                if(this.tokenUtils.validateToken(authToken, details)) {
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
