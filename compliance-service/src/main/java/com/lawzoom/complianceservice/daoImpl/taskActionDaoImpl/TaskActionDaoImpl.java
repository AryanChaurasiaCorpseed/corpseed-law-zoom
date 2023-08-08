package com.lawzoom.complianceservice.daoImpl.taskActionDaoImpl;

import com.lawzoom.complianceservice.dao.taskActionDao.TaskActionDao;
import com.lawzoom.complianceservice.model.complianceSubTaskModel.ComplianceSubTask;
import com.lawzoom.complianceservice.model.complianceTaskModel.ComplianceTask;
import com.lawzoom.complianceservice.model.taskActionModel.TaskAction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TaskActionDaoImpl implements TaskActionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<TaskAction> findTaskActionsByComplianceTask(ComplianceTask complianceTask) {
        return this.sessionFactory.getCurrentSession().createCriteria(TaskAction.class)
                .add(Restrictions.eq("complianceTask",complianceTask))
                .list();
    }

    @Override
    @Transactional
    public List<TaskAction> findTaskActionsByComplianceSubTask(ComplianceSubTask complianceSubTask) {
        return this.sessionFactory.getCurrentSession().createCriteria(TaskAction.class)
                    .add(Restrictions.eq("complianceSubTask", complianceSubTask)).list();
    }

    @Override
    public TaskAction saveTaskAction(TaskAction taskAction) {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(taskAction);
            tx.commit();
            return taskAction;
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
            return null;
        }finally {
            if(session==null)session.close();
        }
    }

    @Override
    public TaskAction updateTaskAction(TaskAction taskAction) {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.update(taskAction);
            tx.commit();
            return taskAction;
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
            return null;
        }finally {
            if(session==null)session.close();
        }
    }

    @Override
    @Transactional
    public TaskAction findTaskActionByComplianceTaskAndId(ComplianceTask complianceTask, Long actionId) {
        return (TaskAction) this.sessionFactory.getCurrentSession().createCriteria(TaskAction.class)
                .add(Restrictions.and(Restrictions.eq("complianceTask",complianceTask),
                        Restrictions.eq("id",actionId))).setMaxResults(1).uniqueResult();
    }

    @Override
    @Transactional
    public TaskAction findTaskActionByComplianceSubTaskAndId(ComplianceSubTask complianceSubTask, Long actionId) {
        return (TaskAction) this.sessionFactory.getCurrentSession().createCriteria(TaskAction.class)
                .add(Restrictions.and(Restrictions.eq("complianceSubTask",complianceSubTask),
                        Restrictions.eq("id",actionId))).setMaxResults(1).uniqueResult();
    }

    @Override
    public boolean deleteTaskAction(TaskAction taskAction) {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.delete(taskAction);
            tx.commit();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
            return false;
        }finally {
            if(session==null)session.close();
        }
    }
}
