package com.master.daoImpl.cityDaoImpl;

import com.master.dao.cityDao.CityDao;
import com.master.model.cityModel.City;
import com.master.model.stateModel.State;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class CityDaoImpl implements CityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public List<City> findCityByState(State state) {
        return this.sessionFactory.getCurrentSession().createCriteria(City.class)
                .add(Restrictions.eq("state",state)).list();
    }
}
