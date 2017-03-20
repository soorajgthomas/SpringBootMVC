package com.sooraj.springboot.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.sooraj.springboot.Util.CommonUtils;
import com.sooraj.springboot.persistence.entities.impl.Country;
import com.sooraj.springboot.persistence.entities.impl.Employee;
import com.sooraj.springboot.service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by SOORAJ on 04-03-2017.
 */

@Controller
public class EmployeController {

    @Autowired
    EmployeeService employeeService;

    @ModelAttribute("employee")
    public Employee construct() {
        return new Employee();
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ModelAndView employeePage(HttpServletRequest request,
                                     @RequestParam(value = "status", required = false, defaultValue = "0") String status,
                                     @RequestParam(value = "p", required = false, defaultValue = "1") String pageNumber) {

        ModelAndView model = new ModelAndView();
        model.addObject("saveSuccess", status);

        employeeService.setViewData(model, pageNumber, request);

        model.setViewName("employee");
        return model;
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public ModelAndView submit(@Valid @ModelAttribute("employee")Employee employee, BindingResult bindingResult,
                               @RequestParam(value="nationality") Long nationality,
                               @RequestParam(value="emiratesIdExpiry") String emiratesIdExpiry,
                               @RequestParam(value="passportExpiry") String passportExpiry,
                               @RequestParam(value="healthCardExpiry") String healthCardExpiry,
                               @RequestParam(value="dateOfBirth") String dateOfBirth,
                               @RequestParam(value="dateOfJoin") String dateOfJoin,
                               @RequestParam(value="createdTime") String createdTime) throws ParseException {

        System.out.println(employee.getFirstName());

        employee.setNationality(new Country(nationality));
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        employee.setEmiratesIdExpiry(sdf.parse(emiratesIdExpiry));
        employee.setPassportExpiry(sdf.parse(passportExpiry));
        employee.setHealthCardExpiry(sdf.parse(healthCardExpiry));
        employee.setDateOfBirth(sdf.parse(dateOfBirth));
        employee.setDateOfJoin(sdf.parse(dateOfJoin));

        int status = 0;
        if(employee.getId() == 0) {

            employee.setCreatedTime(new Date());
            employee.setModifiedTime(new Date());
            employee.setStatus(true);
            status = employeeService.saveData(employee);
        } else {
            employee.setCreatedTime(sdf.parse(createdTime));
            employee.setModifiedTime(new Date());
            employeeService.updateEmployee(employee);
            status = employee.getId().intValue();
        }

        ModelAndView modelAndView = new ModelAndView("redirect:" + "/employee");
        modelAndView.addObject("status", status);
        return modelAndView;
    }

    @RequestMapping(value = "/getEmployeeById", method = RequestMethod.POST)
    public @ResponseBody
    String  getEmployeeById(@RequestParam(value="id") String input) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("\n\ninput : "+input);
        Employee inputVal = mapper.readValue(input,Employee.class);
        Employee employee = employeeService.getEmplyeeDetailsById(inputVal.getId());
        System.out.println(toJson(employee));
        return toJson(employee);

    }


    @RequestMapping(value = "/deactivateEmployeeById", method = RequestMethod.POST)
    public @ResponseBody
    String deactivateCountryById(@RequestParam(value="id") String input) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Country inputVal = mapper.readValue(input,Country.class);
        Boolean status = employeeService.deactivateEmployeeById(inputVal.getId());
        return status ? "success" : "failed";

    }

    private String toJson(Employee employee) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String value = mapper.writeValueAsString(employee);
            return value;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
