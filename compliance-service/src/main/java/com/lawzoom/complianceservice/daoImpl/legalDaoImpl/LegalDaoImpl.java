package com.lawzoom.complianceservice.daoImpl.legalDaoImpl;

import com.lawzoom.complianceservice.dao.legalDao.LegalDao;
import com.lawzoom.complianceservice.model.complianceGuide.ComplianceGuide;
import com.lawzoom.complianceservice.model.legalGuideModel.LegalGuide;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LegalDaoImpl implements LegalDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<LegalGuide> findLegalGuidesByComplianceGuide(ComplianceGuide complianceGuide) {
        return this.sessionFactory.getCurrentSession().createCriteria(LegalGuide.class)
                .list();
    }

    @Override
    @Transactional
    public LegalGuide findLegalGuideByComplianceGuideAndTitle(ComplianceGuide complianceGuide, String title) {
        return (LegalGuide) this.sessionFactory.getCurrentSession().createCriteria(LegalGuide.class)
                .add(Restrictions.and(Restrictions.eq("complianceGuide",complianceGuide),
                        Restrictions.eq("title",title)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public LegalGuide saveLegalDao(LegalGuide legalGuide) {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(legalGuide);
            tx.commit();
            return legalGuide;
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public LegalGuide findLegalGuideByComplianceGuideAndTitleAndIdNot(ComplianceGuide complianceGuide, String title, Long id) {
        return (LegalGuide) this.sessionFactory.getCurrentSession().createCriteria(LegalGuide.class)
                .add(Restrictions.and(Restrictions.eq("complianceGuide",complianceGuide),
                        Restrictions.eq("title",title)))
                .add(Restrictions.ne("id",id)).setMaxResults(1).uniqueResult();
    }

    @Override
    public LegalGuide updtaeLegalDao(LegalGuide legalGuide) {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.update(legalGuide);
            tx.commit();
            return legalGuide;
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public LegalGuide findLegalGuideByComplianceGuideAndId(ComplianceGuide complianceGuide, Long legalId) {
        return (LegalGuide) this.sessionFactory.getCurrentSession().createCriteria(LegalGuide.class)
                .add(Restrictions.and(Restrictions.eq("complianceGuide",complianceGuide),
                        Restrictions.eq("id",legalId)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public boolean deleteLegalGuide(LegalGuide legalGuide) {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.delete(legalGuide);
            tx.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
            return false;
        }finally {
            if(session!=null)session.close();
        }
    }
}