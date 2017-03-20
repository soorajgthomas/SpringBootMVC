package com.sooraj.springboot.dao.daoImpl;

import com.sooraj.springboot.dao.dao.CountryDao;
import com.sooraj.springboot.persistence.entities.impl.Country;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SOORAJ on 19-03-2017.
 */

@Repository
@Transactional
public class CountryDaoImpl implements CountryDao{

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public int saveData(Country country) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(country);
        return country.getId().intValue();
    }

    @Override
    public void updateCountry(Country country) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(country);
    }

    @Override
    public List<Country> getAllCountries() {
        Criteria c=sessionFactory.getCurrentSession().createCriteria(Country.class);
        return c.list();
    }


    @Override
    public List<Country> getAllCountriesByStatus() {
        Criteria c=sessionFactory.getCurrentSession().createCriteria(Country.class);
        c.add(Restrictions.eq("status", 1));
        return c.list();
    }

    @Override
    public List<Country> getCountriesWithinRange(int start, int end) {
        Session session = sessionFactory.openSession();
        Criteria c = session.createCriteria(Country.class);
        c.setFirstResult(start);
        c.setMaxResults(end);
        List<Country> countryList = c.list();
        return countryList;
    }

    @Override
    public List<Country> getCountriesWithinRangeByStatus(int start, int end) {
        Session session = sessionFactory.openSession();
        Criteria c = session.createCriteria(Country.class);
        c.add(Restrictions.eq("status", true));
        c.setFirstResult(start);
        c.setMaxResults(end);
        List<Country> countryList = c.list();
        return countryList;
    }

    @Override
    public Long getCountryCount() {
        Session session = sessionFactory.openSession();
        return (Long) session.createCriteria(Country.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getCountryCountByStatus() {
        Session session = sessionFactory.openSession();
        Criteria c = session.createCriteria(Country.class);
        c.add(Restrictions.eq("status", true));
        return (Long) c.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Country getCountryDetailsById(long id) {
        Country country = new Country();
        try {
            Query q = sessionFactory.getCurrentSession().createQuery(
                    "from Country where id= :cod");
            q.setParameter("cod", id);
            country = (Country) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }

    @Override
    public Boolean deactivateCountryById(long id) {
        Country country = new Country();
        try {

            Query q = sessionFactory.getCurrentSession().createQuery(
                    "from Country where id= :cod");
            q.setParameter("cod", id);
            country = (Country) q.uniqueResult();

            country.setStatus(false);
            updateCountry(country);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
