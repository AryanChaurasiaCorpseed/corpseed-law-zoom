package com.master.daoImpl.complianceHubDaoImpl;

import com.master.dao.complianceHubDao.ComplianceHubDao;
import com.master.model.complianceHubModel.ComplianceHub;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ComplianceHubDaoImpl implements ComplianceHubDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<ComplianceHub> findComplianceHubByBusinessActivity(String businessActivity) {
        return this.sessionFactory.getCurrentSession().createCriteria(ComplianceHub.class)
                .add(Restrictions.eq("businessActivity",businessActivity)).list();
    }
}
