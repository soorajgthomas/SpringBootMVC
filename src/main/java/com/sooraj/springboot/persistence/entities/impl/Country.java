package com.sooraj.springboot.persistence.entities.impl;

import com.sooraj.springboot.persistence.entities.construct.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by SOORAJ on 04-03-2017.
 */
@Entity
@Table(name = "country")
public class Country extends AbstractEntity<Long> {

    @Column(length = 50, nullable = false)
    private String countryName;

    @Column(length = 15, nullable = false)
    private String countryShortName;

    @Column(length = 50, nullable = false)
    private String currencyName;

    @Column(length = 1, nullable = false)
    private Boolean status;

    @Column()
    private Date createdTime;

    @Column()
    private Date modifiedTime;

    public Country() {
        super(Country.class);
    }

    public Country(Long id) {
        super(Country.class);
        setId(id);
    }

    public Country(Country country) {
        super(Country.class);
        this.countryName = country.getCountryName();
        this.countryShortName = country.getCountryShortName();
        this.currencyName = country.getCurrencyName();
        this.status = country.getStatus();
        this.createdTime = country.getCreatedTime();
        this.modifiedTime = country.getModifiedTime();
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryShortName() {
        return countryShortName;
    }

    public void setCountryShortName(String countryShortName) {
        this.countryShortName = countryShortName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
