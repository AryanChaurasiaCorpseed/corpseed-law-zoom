package com.master.daoImpl.stateDaoImpl;

import com.master.dao.stateDao.StateDao;
import com.master.model.countryModel.Country;
import com.master.model.stateModel.State;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StateDaoImpl implements StateDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public List<State> findStateByCountry(Country country) {
        return this.sessionFactory.getCurrentSession().createCriteria(State.class)
                .add(Restrictions.eq("country",country)).list();
    }

    @Transactional
    @Override
    public State findStateById(Long stateId) {
        return this.sessionFactory.getCurrentSession().get(State.class,stateId);
    }
}
