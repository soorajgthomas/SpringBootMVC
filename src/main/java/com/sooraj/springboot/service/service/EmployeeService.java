package com.sooraj.springboot.service.service;

import com.sooraj.springboot.persistence.entities.impl.Employee;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by SOORAJ on 05-03-2017.
 */
public interface EmployeeService {

    public void setViewData(ModelAndView model, String pageNumber, HttpServletRequest request);

    public int saveData(Employee employee);

    public void updateEmployee(Employee employee);

    public List<Employee> getAllEmployee();

    public List<Employee> getPaginatedEmployee(int pageNumber, HttpServletRequest request);

    public Long getEmployeeCount(HttpServletRequest request);

    public Employee getEmplyeeDetailsById(long id);

    public Boolean deactivateEmployeeById(long id);

}
