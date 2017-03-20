package com.sooraj.springboot.service.service;

import com.sooraj.springboot.persistence.entities.impl.Country;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by SOORAJ on 19-03-2017.
 */
public interface CountryService {

    public void setViewData(ModelAndView model, String pageNumber, HttpServletRequest request);

    public int saveData(Country country);

    public void updateCountry(Country country);

    public List<Country> getAllCountry();

    public List<Country> getAllCountriesByStatus();

    public List<Country> getPaginatedCountry(int pageNumber, HttpServletRequest request);

    public Long getCountryCount(HttpServletRequest request);

    public Country getCountryDetailsById(long id);

    public Boolean deactivateCountryById(long id);


}
