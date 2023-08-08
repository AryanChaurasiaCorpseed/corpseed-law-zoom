package com.lawzoom.complianceservice.daoImpl.complianceSubTaskDaoImpl;

import com.lawzoom.complianceservice.dao.complianceSubTaskDao.ComplianceSubTaskDao;
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
public class ComplianceSubTaskDaoImpl implements ComplianceSubTaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<ComplianceSubTask> findComplianceSubTaskByComplianceTask(ComplianceTask complianceTask) {
        return this.sessionFactory.getCurrentSession().createCriteria(ComplianceSubTask.class)
                .add(Restrictions.eq("complianceTask",complianceTask)).list();
    }

    @Override
    @Transactional
    public ComplianceSubTask findSubTaskByComplianceTaskAndName(ComplianceTask complianceTask, String subTaskName) {
        return (ComplianceSubTask) this.sessionFactory.getCurrentSession().createCriteria(ComplianceSubTask.class)
                .add(Restrictions.and(Restrictions.eq("complianceTask",complianceTask),
                        Restrictions.eq("subTaskName",subTaskName)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public ComplianceSubTask saveComplianceSubTask(ComplianceSubTask complianceSubTask) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(complianceSubTask);
            tx.commit();
            return complianceSubTask;
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
    public ComplianceSubTask findSubTaskByComplianceAndNameAndIdNot(ComplianceTask complianceTask, String subTaskName, Long id) {
        return (ComplianceSubTask) this.sessionFactory.getCurrentSession().createCriteria(ComplianceSubTask.class)
                .add(Restrictions.and(Restrictions.eq("complianceTask",complianceTask),
                        Restrictions.eq("subTaskName",subTaskName)))
                .add(Restrictions.ne("id",id)).setMaxResults(1).uniqueResult();
    }

    @Override
    public ComplianceSubTask updateComplianceSubTask(ComplianceSubTask complianceSubTask) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.update(complianceSubTask);
            tx.commit();
            return complianceSubTask;
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
    public ComplianceSubTask findSubTaskByTaskAndId(ComplianceTask complianceTask, Long subTaskId) {
        return (ComplianceSubTask) this.sessionFactory.getCurrentSession().createCriteria(ComplianceSubTask.class)
                .add(Restrictions.and(Restrictions.eq("complianceTask",complianceTask),
                        Restrictions.eq("id",subTaskId)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public boolean deleteComplianceSubTask(ComplianceSubTask complianceSubTask) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.delete(complianceSubTask);
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
    public ComplianceSubTask findComplianceSubTaskById(Long subTaskId) {
        return this.sessionFactory.getCurrentSession().get(ComplianceSubTask.class,subTaskId);
    }
}
