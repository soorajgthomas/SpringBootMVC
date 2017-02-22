package com.sooraj.springboot.web.manager;



import com.sooraj.springboot.persistence.entities.impl.User;
import com.sooraj.springboot.persistence.entities.impl.UserRole;

import java.util.Collection;

/**
 * Created by SOORAJ on 11-12-2016.
 */
public interface UserManager <M> {
    public void refreshUser(User user);
    public Boolean saveUser(User user);
    public Boolean updateUser(User user);
    public Boolean deleteUser(User user);
    public User getUser(User user);
    public User getUserById(User user);
    public User getUserByName(User user);
    public Collection<User> getAllUser();
    public UserRole getUserRole(String roleName);
}
