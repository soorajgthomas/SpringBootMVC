package com.sooraj.springboot.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by SOORAJ on 11-12-2016.
 */
public class UserDetailsImpl extends User implements UserDetails {

    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserDetailsImpl(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    private com.sooraj.springboot.persistence.entities.impl.User user;


    public com.sooraj.springboot.persistence.entities.impl.User getUser() {
        return user;
    }

    public void setUser(com.sooraj.springboot.persistence.entities.impl.User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserDetailsImpl{" +
                "user=" + user +
                '}';
    }
}

