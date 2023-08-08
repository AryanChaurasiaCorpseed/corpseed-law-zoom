package com.lawzoom.companyservice.daoImpl.gstDaoImpl;

import com.lawzoom.companyservice.dao.gstDao.GstDao;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.gstModel.Gst;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GstDaoImpl implements GstDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Gst findGstByCompanyAndGstNumber(Company company, String gstNumber) {
        return (Gst) this.sessionFactory.getCurrentSession().createCriteria(Gst.class)
                .add(Restrictions.and(Restrictions.eq("company",company),
                        Restrictions.eq("gstNumber",gstNumber)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public Gst saveGst(Gst gst) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(gst);
            tx.commit();
            return gst;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public Gst findGstByCompanyAndGstNumberAndIdNot(Company company, String gstNumber, Long gstId) {
        return (Gst) this.sessionFactory.getCurrentSession().createCriteria(Gst.class)
                .add(Restrictions.and(Restrictions.eq("company",company),
                        Restrictions.eq("gstNumber",gstNumber)))
                .add(Restrictions.ne("id",gstId))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public Gst updateGst(Gst gst) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.update(gst);
            tx.commit();
            return gst;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public Gst findGstByCompanyAndId(Company company, Long gstId) {
        return (Gst) this.sessionFactory.getCurrentSession().createCriteria(Gst.class)
                .add(Restrictions.and(Restrictions.eq("company", company),
                        Restrictions.eq("id", gstId))).setMaxResults(1).uniqueResult();
    }

    @Override
    public boolean deleteGst(Gst gst) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.delete(gst);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public Gst findGstById(Long gstId) {
        return this.sessionFactory.getCurrentSession().get(Gst.class,gstId);
    }

    @Transactional
    @Override
    public Gst findGstByGstNumber(String gstNumber) {
        return (Gst) this.sessionFactory.getCurrentSession().createCriteria(Gst.class)
                .add(Restrictions.eq("gstNumber",gstNumber))
                .setMaxResults(1).uniqueResult();
    }
}
