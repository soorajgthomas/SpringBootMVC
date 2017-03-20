package com.sooraj.springboot.dao.dao;

import com.sooraj.springboot.persistence.entities.impl.Country;
import com.sooraj.springboot.persistence.entities.impl.Employee;

import java.util.List;

/**
 * Created by SOORAJ on 19-03-2017.
 */
public interface CountryDao {

    public int saveData(Country country);

    public void updateCountry(Country country);

    public List<Country> getAllCountries();

    public List<Country> getAllCountriesByStatus();

    public List<Country> getCountriesWithinRange(int start, int end);

    public List<Country> getCountriesWithinRangeByStatus(int start, int end);

    public Long getCountryCount();

    public Long getCountryCountByStatus();

    public Country getCountryDetailsById(long id);

    public Boolean deactivateCountryById(long id);

}
