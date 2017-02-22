package com.sooraj.springboot.persistence.entities.impl;

import com.sooraj.springboot.persistence.entities.construct.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by SOORAJ on 11-12-2016.
 */
@Entity
@Table(name = "UserRole")
public final class UserRole extends AbstractEntity<Long> {

    @Column(nullable = false, length = 100)
    private String name;

    public UserRole() {
        super(UserRole.class);
    }

    public UserRole(String name) {
        super(UserRole.class);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "name='" + name + '\'' +
                '}';
    }
}
