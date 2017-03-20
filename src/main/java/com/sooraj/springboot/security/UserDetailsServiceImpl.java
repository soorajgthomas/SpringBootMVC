package com.sooraj.springboot.security;


import com.sooraj.springboot.persistence.entities.impl.User;
import com.sooraj.springboot.persistence.entities.impl.UserRole;
import com.sooraj.springboot.web.manager.UserManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by SOORAJ on 11-12-2016.
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

    @Autowired(required = true)
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        logger.debug("UserDetailsServiceImpl loadUserByUsername() ");
        try {
            User bean = new User(username);
            bean = this.userManager.getUser(bean);
            final Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(bean.getUserRoles().size());
            for (UserRole role : bean.getUserRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            final UserDetailsImpl details = new UserDetailsImpl(bean.getUserName(), bean.getUserPwd(), bean.getEnabled(),
                    true, true, true, authorities);
            details.setUser(bean);
            return details;
        } catch (Exception e) {
            logger.error("Unable to find record with username:=" + username, e);
        }
        return null;
    }

}
