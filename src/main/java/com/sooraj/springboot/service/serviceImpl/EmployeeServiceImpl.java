package com.sooraj.springboot.service.serviceImpl;

import com.sooraj.springboot.Util.CommonUtils;
import com.sooraj.springboot.dao.dao.CountryDao;
import com.sooraj.springboot.dao.dao.EmployeeDao;
import com.sooraj.springboot.persistence.entities.impl.Country;
import com.sooraj.springboot.persistence.entities.impl.Employee;
import com.sooraj.springboot.service.service.EmployeeService;
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
 * Created by SOORAJ on 05-03-2017.
 */

@Service
@PropertySource({"classpath:properties/project.properties"})
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    CountryDao countryDao;

    @Value("${project.numberOfResultPerPage}")
    int numberOfResultPerPage;

    @Override
    public void setViewData(ModelAndView model, String pageNumber, HttpServletRequest request) {
        List<Country> countries = countryDao.getAllCountries();
        model.addObject("countries", countries);

        Map< String, String > gender = new HashMap<>();
        gender.put("Male", "Male");
        gender.put("Female", "Female");
        model.addObject("gender", gender);


        int currentPageNumber;
        if(CommonUtils.isPositiveNumeric(pageNumber)) {
            currentPageNumber = Integer.parseInt(pageNumber);
        } else {
            currentPageNumber = 1;
        }
        List<Employee> employeeList = getPaginatedEmployee(currentPageNumber, request);
        model.addObject("employeeList", employeeList);
        model.addObject("currentPageNumber", currentPageNumber);

        Long numberOfEmployees = getEmployeeCount(request);
        if(numberOfEmployees % numberOfResultPerPage == 0) {
            model.addObject("numberOfPages", (numberOfEmployees / numberOfResultPerPage));
        } else{
            model.addObject("numberOfPages", (numberOfEmployees / numberOfResultPerPage) + 1);
        }
    }

    @Override
    public int saveData(Employee employee) {
        return employeeDao.saveData(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    public List<Employee> getAllEmployee() {
        return  employeeDao.getAllEmployee();
    }

    public List<Employee> getPaginatedEmployee(int pageNumber, HttpServletRequest request) {
        if(Integer.valueOf(numberOfResultPerPage) != null) {
            int maxResults = numberOfResultPerPage;
            int startRange = 0;
            if (pageNumber>1) {
                startRange = (pageNumber - 1) * numberOfResultPerPage;
            }
            if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_SUPERADMIN")) {
                return employeeDao.getEmployeesWithinRange(startRange, maxResults);
            } else {
                return employeeDao.getEmployeesWithinRangeByStatus(startRange, maxResults);
            }
        } else {
            if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_SUPERADMIN")) {
                return employeeDao.getEmployeesWithinRange(0, 10);
            } else {
                return employeeDao.getEmployeesWithinRangeByStatus(0, 10);
            }
        }

    }

    public Long getEmployeeCount(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_SUPERADMIN")) {
            return employeeDao.getEmployeeCount();
        } else {
            return employeeDao.getEmployeeCountByStatus();
        }

    }

    @Override
    public Employee getEmplyeeDetailsById(long id) {
        return employeeDao.getEmplyeeDetailsById(id);
    }

    @Override
    public Boolean deactivateEmployeeById(long id) {
        return employeeDao.deactivateEmployeeById(id);
    }


}
