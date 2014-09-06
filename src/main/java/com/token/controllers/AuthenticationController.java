package com.token.controllers;

import com.google.common.collect.Maps;
import com.token.helpers.TokenUtils;
import com.token.models.UserTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class AuthenticationController {

    private TokenUtils tokenUtils = new TokenUtils();

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ResponseBody
    public UserTransfer authorize(@RequestParam String username, @RequestParam String password) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails details = this.userDetailService.loadUserByUsername(username);

        Map<String, Boolean> roles = Maps.newHashMap();
        for(GrantedAuthority authority : details.getAuthorities()){
            roles.put(authority.toString(), Boolean.TRUE);
        }

        return new UserTransfer(details.getUsername(), roles, tokenUtils.createToken(details));
    }
}
