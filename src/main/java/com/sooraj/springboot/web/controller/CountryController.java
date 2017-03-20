package com.sooraj.springboot.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sooraj.springboot.persistence.entities.impl.Country;
import com.sooraj.springboot.persistence.entities.impl.Employee;
import com.sooraj.springboot.security.UserDetailsServiceImpl;
import com.sooraj.springboot.service.service.CountryService;
import com.sooraj.springboot.service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SOORAJ on 04-03-2017.
 */

@Controller
public class CountryController {

    @Autowired
    CountryService countryService;

    @ModelAttribute("country")
    public Country construct() {
        return new Country();
    }

    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public ModelAndView employeePage(HttpServletRequest request,
                                     @RequestParam(value = "status", required = false, defaultValue = "0") String status,
                                     @RequestParam(value = "p", required = false, defaultValue = "1") String pageNumber) {

        ModelAndView model = new ModelAndView();
        model.addObject("saveSuccess", status);

        countryService.setViewData(model, pageNumber, request);

        model.setViewName("country");
        return model;
    }

    @RequestMapping(value = "/addCountry", method = RequestMethod.POST)
    public ModelAndView submit(@Valid @ModelAttribute("employee")Country country,
                               BindingResult bindingResult,
                               @RequestParam(value="createdTime") String createdTime) throws ParseException {

        int status = 0;
        if(country.getId() == 0) {
            country.setCreatedTime(new Date());
            country.setModifiedTime(new Date());
            country.setStatus(true);
            status = countryService.saveData(country);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            country.setCreatedTime(sdf.parse(createdTime));
            country.setModifiedTime(new Date());
            countryService.updateCountry(country);
            status = country.getId().intValue();
        }

        ModelAndView modelAndView = new ModelAndView("redirect:" + "/country");
        modelAndView.addObject("status", status);
        return modelAndView;
    }

    @RequestMapping(value = "/getCountryById", method = RequestMethod.POST)
    public @ResponseBody
    String  getEmployeeById(@RequestParam(value="id") String input) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Country inputVal = mapper.readValue(input,Country.class);
        Country country = countryService.getCountryDetailsById(inputVal.getId());
        System.out.println(toJson(country));
        return toJson(country);

    }


    @RequestMapping(value = "/deactivateCountryById", method = RequestMethod.POST)
    public @ResponseBody
    String deactivateCountryById(@RequestParam(value="id") String input) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Country inputVal = mapper.readValue(input,Country.class);
        Boolean status = countryService.deactivateCountryById(inputVal.getId());
        return status ? "success" : "failed";

    }


    private String toJson(Country country) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String value = mapper.writeValueAsString(country);
            return value;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
