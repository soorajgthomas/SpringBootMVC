package com.sooraj.springboot.service.serviceImpl;

import com.sooraj.springboot.Util.CommonUtils;
import com.sooraj.springboot.dao.dao.CountryDao;
import com.sooraj.springboot.persistence.entities.impl.Country;
import com.sooraj.springboot.service.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SOORAJ on 19-03-2017.
 */

@Service
@PropertySource({"classpath:properties/project.properties"})
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryDao countryDao;

    @Value("${project.numberOfResultPerPage}")
    int numberOfResultPerPage;

    @Override
    public void setViewData(ModelAndView model, String pageNumber, HttpServletRequest request) {

        int currentPageNumber;
        if(CommonUtils.isPositiveNumeric(pageNumber)) {
            currentPageNumber = Integer.parseInt(pageNumber);
        } else {
            currentPageNumber = 1;
        }
        List<Country> countryList = getPaginatedCountry(currentPageNumber, request);
        model.addObject("countryList", countryList);
        model.addObject("currentPageNumber", currentPageNumber);

        Long numberOfCountries = getCountryCount(request);
        if(numberOfCountries % numberOfResultPerPage == 0) {
            model.addObject("numberOfPages", (numberOfCountries / numberOfResultPerPage));
        } else{
            model.addObject("numberOfPages", (numberOfCountries / numberOfResultPerPage) + 1);
        }
    }

    @Override
    public int saveData(Country country) {
        return countryDao.saveData(country);
    }

    @Override
    public void updateCountry(Country country) {
        countryDao.updateCountry(country);
    }

    @Override
    public List<Country> getAllCountry(){
        return  countryDao.getAllCountries();
    }

    public List<Country> getAllCountriesByStatus(){
        return  countryDao.getAllCountries();
    }

    @Override
    public List<Country> getPaginatedCountry(int pageNumber, HttpServletRequest request) {
        if(Integer.valueOf(numberOfResultPerPage) != null) {
            int maxResults = numberOfResultPerPage;
            int startRange = 0;
            if (pageNumber>1) {
                startRange = (pageNumber - 1) * numberOfResultPerPage;
            }

            if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_SUPERADMIN")) {
                return countryDao.getCountriesWithinRange(startRange, maxResults);
            } else {
                return countryDao.getCountriesWithinRangeByStatus(startRange, maxResults);
            }

        } else {
            if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_SUPERADMIN")) {
                return countryDao.getCountriesWithinRange(0, 10);
            } else {
                return countryDao.getCountriesWithinRangeByStatus(0, 10);
            }
        }

    }

    @Override
    public Long getCountryCount(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_SUPERADMIN")) {
            return countryDao.getCountryCount();
        } else {
            return countryDao.getCountryCountByStatus();
        }
    }

    @Override
    public Country getCountryDetailsById(long id) {
        return countryDao.getCountryDetailsById(id);
    }

    @Override
    public Boolean deactivateCountryById(long id) {
        return countryDao.deactivateCountryById(id);
    }


}
