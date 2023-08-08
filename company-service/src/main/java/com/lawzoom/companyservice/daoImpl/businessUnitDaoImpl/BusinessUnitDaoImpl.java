package com.lawzoom.companyservice.daoImpl.businessUnitDaoImpl;

import com.lawzoom.companyservice.dao.businessUnitDao.BusinessUnitDao;
import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
import com.lawzoom.companyservice.model.gstModel.Gst;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BusinessUnitDaoImpl implements BusinessUnitDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public BusinessUnit saveBusinessUnit(BusinessUnit businessUnit) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(businessUnit);
            tx.commit();
            return businessUnit;
        } catch (Exception e) {
            tx.rollback();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    public BusinessUnit updateBusinessUnit(BusinessUnit businessUnit) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.update(businessUnit);
            tx.commit();
            return businessUnit;
        } catch (Exception e) {
            tx.rollback();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public BusinessUnit findBusinessUnitByGstAndId(Gst gst, Long businessUnitId) {
        return (BusinessUnit) this.sessionFactory.getCurrentSession().createCriteria(BusinessUnit.class)
                .add(Restrictions.and(Restrictions.eq("id", businessUnitId),
                        Restrictions.eq("gst", gst)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public boolean deleteBusinessUnit(BusinessUnit businessUnit) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.delete(businessUnit);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            return false;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public BusinessUnit findBusinessUnitById(Long businessUnitId) {
        return this.sessionFactory.getCurrentSession().get(BusinessUnit.class,businessUnitId);
    }

    @Transactional
    @Override
    public List<BusinessUnit> findBusinessUnitByGst(Gst gst) {
        return this.sessionFactory.getCurrentSession().createCriteria(BusinessUnit.class)
                .add(Restrictions.eq("gst",gst)).list();
    }
}
