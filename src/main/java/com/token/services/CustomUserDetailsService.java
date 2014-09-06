package com.token.services;

import com.google.common.collect.Lists;
import com.token.models.SimpleUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    public static final String ROLE_USER = "USER";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetails> details = Lists.newArrayList(new SimpleUserDetails("user", "user", ROLE_USER));

        return details.stream()
                .filter(d -> d.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }
}
