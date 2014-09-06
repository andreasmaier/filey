package com.token.models;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class SimpleUserDetails implements UserDetails{

    private String username;
    private String password;
    private boolean enabled = true;
    private Set<GrantedAuthority> authorities = Sets.newHashSet();

    public SimpleUserDetails(String username, String password, String... extraRoles) {
        this.username = username;
        this.password = password;

        List<String> roles = Arrays.asList(extraRoles);

        roles.stream().map( r -> authorities.add(new SimpleGrantedAuthority(role(r))) );
    }

    private String role(String r) {
        return "ROLE_" + r;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
