package com.token.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority{

    @Id
    @GeneratedValue
    private long id;

    private String authority;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="username")
    private User user;

    public User getUser() {
        return user;
    }

    public String getAuthority() {
        return authority;
    }
}
