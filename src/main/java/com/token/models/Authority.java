package com.token.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String authority;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="username")
    private User user;

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
