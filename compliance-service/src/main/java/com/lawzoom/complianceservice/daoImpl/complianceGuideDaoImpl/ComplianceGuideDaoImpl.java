package com.lawzoom.complianceservice.daoImpl.complianceGuideDaoImpl;

import com.lawzoom.complianceservice.dao.complianceGuideDao.ComplianceGuideDao;
import com.lawzoom.complianceservice.model.complianceGuide.ComplianceGuide;
import com.lawzoom.complianceservice.model.complianceSubTaskModel.ComplianceSubTask;
import com.lawzoom.complianceservice.model.complianceTaskModel.ComplianceTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ComplianceGuideDaoImpl implements ComplianceGuideDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<ComplianceGuide> findTaskGuidesByTask(ComplianceTask complianceTask) {
        return this.sessionFactory.getCurrentSession().createCriteria(ComplianceGuide.class)
                .add(Restrictions.eq("complianceTask",complianceTask)).list();
    }

    @Override
    @Transactional
    public ComplianceGuide findTaskGuideByTaskAndJurisdiction(ComplianceTask complianceTask, String jurisdiction) {
        return (ComplianceGuide) this.sessionFactory.getCurrentSession().createCriteria(ComplianceGuide.class)
                .add(Restrictions.and(Restrictions.eq("complianceTask",complianceTask),
                        Restrictions.eq("jurisdiction",jurisdiction)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    @Transactional
    public ComplianceGuide findComplianceGuideById(Long guideId) {
        return (ComplianceGuide) this.sessionFactory.getCurrentSession().createCriteria(ComplianceGuide.class)
                .add(Restrictions.eq("id",guideId))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public ComplianceGuide saveComplianceGuide(ComplianceGuide complianceGuide) {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(complianceGuide);
            tx.commit();
            return complianceGuide;
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public ComplianceGuide findGuideByTaskAndJurisdictionAndIdNot(ComplianceTask complianceTask,String jurisdiction, Long id) {
        return (ComplianceGuide) this.sessionFactory.getCurrentSession().createCriteria(ComplianceGuide.class)
                .add(Restrictions.and(Restrictions.eq("complianceTask",complianceTask),
                        Restrictions.eq("jurisdiction",jurisdiction)))
                .add(Restrictions.ne("id",id))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public ComplianceGuide updateComplianceGuide(ComplianceGuide complianceGuide) {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.update(complianceGuide);
            tx.commit();
            return complianceGuide;
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public ComplianceGuide findGuideByTaskAndId(ComplianceTask complianceTask, Long guideId) {
        return (ComplianceGuide) this.sessionFactory.getCurrentSession().createCriteria(ComplianceGuide.class)
                .add(Restrictions.and(Restrictions.eq("complianceTask",complianceTask),
                        Restrictions.eq("id",guideId)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public boolean deleteComplianceGuide(ComplianceGuide complianceGuide) {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.delete(complianceGuide);
            tx.commit();
            return true;
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public List<ComplianceGuide> findGuidesBySubTask(ComplianceSubTask complianceSubTask) {
        return this.sessionFactory.getCurrentSession().createCriteria(ComplianceGuide.class)
                .add(Restrictions.eq("complianceSubTask",complianceSubTask)).list();
    }

    @Override
    @Transactional
    public ComplianceGuide findGuideBySubTaskAndJurisdiction(ComplianceSubTask complianceSubTask,String jurisdiction) {
        return (ComplianceGuide) this.sessionFactory.getCurrentSession().createCriteria(ComplianceGuide.class)
                .add(Restrictions.and(Restrictions.eq("complianceSubTask",complianceSubTask),
                        Restrictions.eq("jurisdiction",jurisdiction)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    @Transactional
    public ComplianceGuide findGuideBySubTaskAndJurisdictionAndIdNot(ComplianceSubTask complianceSubTask,String jurisdiction, Long id) {
        return (ComplianceGuide) this.sessionFactory.getCurrentSession().createCriteria(ComplianceGuide.class)
                .add(Restrictions.and(Restrictions.eq("complianceSubTask",complianceSubTask),
                        Restrictions.eq("jurisdiction",jurisdiction)))
                .add(Restrictions.ne("id",id))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    @Transactional
    public ComplianceGuide findGuideBySubTaskAndId(ComplianceSubTask complianceSubTask, Long guideId) {
        return (ComplianceGuide) this.sessionFactory.getCurrentSession().createCriteria(ComplianceGuide.class)
                .add(Restrictions.and(Restrictions.eq("complianceSubTask",complianceSubTask),
                        Restrictions.eq("id",guideId)))
                .setMaxResults(1).uniqueResult();
    }

}
