package com.master.daoImpl.countryDaoImpl;

import com.master.dao.countryDao.CountryDao;
import com.master.model.countryModel.Country;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public List<Country> findAllCountry() {
        return this.sessionFactory.getCurrentSession()
                .createCriteria(Country.class).list();
    }

    @Transactional
    @Override
    public Country findCountryById(Long countryId) {
        return this.sessionFactory.getCurrentSession().get(Country.class,countryId);
    }
}
