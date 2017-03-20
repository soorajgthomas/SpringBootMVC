package com.sooraj.springboot.dao.dao;

import com.sooraj.springboot.persistence.entities.impl.Employee;

import java.util.List;

/**
 * Created by SOORAJ on 05-03-2017.
 */
public interface EmployeeDao {

    public int saveData(Employee employee);

    public void updateEmployee(Employee employee);

    public List<Employee> getAllEmployee();

    public List<Employee> getEmployeesWithinRange(int start, int end);

    public List<Employee> getEmployeesWithinRangeByStatus(int start, int end);

    public Long getEmployeeCount();

    public Long getEmployeeCountByStatus();

    public Employee getEmplyeeDetailsById(long id);

    public Boolean deactivateEmployeeById(long id);

}
