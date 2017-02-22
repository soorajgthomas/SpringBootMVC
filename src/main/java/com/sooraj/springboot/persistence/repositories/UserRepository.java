package com.sooraj.springboot.persistence.repositories;


import com.sooraj.springboot.persistence.entities.impl.User;

import java.util.Collection;

/**
 * Created by SOORAJ on 11-12-2016.
 */
public interface UserRepository<T> {


    public Long size();

    public void refreshUser(User user);

    public Boolean saveUser(User user);

    public User getUser(User user);

    public User getUserById(User user);

    public User getUserByName(User user);

    public Boolean deleteUser(User user);

    public User updateUser(User user);

    public Collection<User> getAllUser();

}
