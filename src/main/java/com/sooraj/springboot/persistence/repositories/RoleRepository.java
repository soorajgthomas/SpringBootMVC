package com.sooraj.springboot.persistence.repositories;



import com.sooraj.springboot.persistence.entities.impl.User;
import com.sooraj.springboot.persistence.entities.impl.UserRole;

import java.util.List;

/**
 * Created by SOORAJ on 11-12-2016.
 */
public interface RoleRepository <T> {

    public Long size();
    public void refreshUser(UserRole userRole);
    public UserRole getRoleByName(String roleName);
    public Boolean saveUserRole(UserRole userRole);
    public Boolean updateUserRole(UserRole userRole);
    public Boolean dropUserRole(UserRole userRole);
    public UserRole getUserRole(User user);
    public List<T> getAllUserRole(User user);
}
