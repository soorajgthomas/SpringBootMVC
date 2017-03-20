package com.sooraj.springboot.dao.daoImpl;

import com.sooraj.springboot.dao.dao.EmployeeDao;
import com.sooraj.springboot.persistence.entities.impl.Country;
import com.sooraj.springboot.persistence.entities.impl.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SOORAJ on 05-03-2017.
 */

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao{

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public int saveData(Employee employee) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(employee);
        return employee.getId().intValue();
    }

    @Override
    public void updateEmployee(Employee employee) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(employee);
    }

    public List<Employee> getAllEmployee() {
        Criteria c=sessionFactory.getCurrentSession().createCriteria(Employee.class);
        return c.list();
    }

    public List<Employee> getEmployeesWithinRange(int start, int end) {
        Session session = sessionFactory.openSession();
        Criteria c = session.createCriteria(Employee.class);
        c.setFirstResult(start);
        c.setMaxResults(end);
        List<Employee> employeeList = c.list();
        return employeeList;
    }

    public List<Employee> getEmployeesWithinRangeByStatus(int start, int end) {
        Session session = sessionFactory.openSession();
        Criteria c = session.createCriteria(Employee.class);
        c.add(Restrictions.eq("status", true));
        c.setFirstResult(start);
        c.setMaxResults(end);
        List<Employee> employeeList = c.list();
        return employeeList;
    }

    public Long getEmployeeCount() {
        Session session = sessionFactory.openSession();
        return (Long) session.createCriteria(Employee.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    public Long getEmployeeCountByStatus() {
        Session session = sessionFactory.openSession();
        Criteria c = session.createCriteria(Employee.class);
        return (Long) c.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Employee getEmplyeeDetailsById(long id) {
        Employee employee = new Employee();
        try {
            Query q = sessionFactory.getCurrentSession().createQuery(
                    "from Employee where id= :cod");
            q.setParameter("cod", id);
            employee = (Employee) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }


    @Override
    public Boolean deactivateEmployeeById(long id) {
        Employee employee = new Employee();
        try {

            Query q = sessionFactory.getCurrentSession().createQuery(
                    "from Employee where id= :cod");
            q.setParameter("cod", id);
            employee = (Employee) q.uniqueResult();

            employee.setStatus(false);
            updateEmployee(employee);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
