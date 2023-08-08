package com.lawzoom.complianceservice.daoImpl.complianceTaskDaoImpl;

import com.lawzoom.complianceservice.dao.complianceTaskDao.ComplianceTaskDao;
import com.lawzoom.complianceservice.model.complianceModel.Compliance;
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
public class ComplianceTaskDaoImpl implements ComplianceTaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<ComplianceTask> findComplianceTaskByCompliance(Compliance compliance) {
        return this.sessionFactory.getCurrentSession().createCriteria(ComplianceTask.class)
                .add(Restrictions.eq("compliance",compliance)).list();
    }

    @Override
    @Transactional
    public ComplianceTask fetchComplianceTaskById(Long taskId) {
        return this.sessionFactory.getCurrentSession().get(ComplianceTask.class,taskId);
    }

    @Override
    @Transactional
    public ComplianceTask findTaskByComplianceAndTaskName(Compliance compliance, String taskName) {
        return (ComplianceTask) this.sessionFactory.getCurrentSession().createCriteria(ComplianceTask.class)
                .add(Restrictions.and(Restrictions.eq("compliance",compliance),
                        Restrictions.eq("taskName",taskName)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public ComplianceTask saveComplianceTask(ComplianceTask complianceTask) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(complianceTask);
            tx.commit();
            return complianceTask;
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
    public ComplianceTask findTaskByComplianceAndTaskNameAndIdNot(Compliance compliance, String taskName, Long id) {
        return (ComplianceTask) this.sessionFactory.getCurrentSession().createCriteria(ComplianceTask.class)
                .add(Restrictions.and(Restrictions.eq("compliance",compliance),
                        Restrictions.eq("taskName",taskName)))
                .add(Restrictions.ne("id",id)).setMaxResults(1).uniqueResult();
    }

    @Override
    public ComplianceTask updateComplianceTask(ComplianceTask complianceTask) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.update(complianceTask);
            tx.commit();
            return complianceTask;
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
    public ComplianceTask fetchTaskByComplianceAndId(Compliance compliance, Long taskId) {
        return (ComplianceTask) this.sessionFactory.getCurrentSession().createCriteria(ComplianceTask.class)
                .add(Restrictions.and(Restrictions.eq("compliance",compliance),
                        Restrictions.eq("id",taskId)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public boolean deleteComplianceTask(ComplianceTask complianceTask) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.delete(complianceTask);
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
    public ComplianceTask findComplianceTaskById(Long taskId) {
        return this.sessionFactory.getCurrentSession().get(ComplianceTask.class,taskId);
    }
}
