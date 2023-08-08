package com.lawzoom.complianceservice.daoImpl.reminderDaoImpl;

import com.lawzoom.complianceservice.dao.reminderDao.ReminderDao;
import com.lawzoom.complianceservice.model.complianceModel.Compliance;
import com.lawzoom.complianceservice.model.complianceSubTaskModel.ComplianceSubTask;
import com.lawzoom.complianceservice.model.complianceTaskModel.ComplianceTask;
import com.lawzoom.complianceservice.model.reminderModel.Reminder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ReminderDaoImpl implements ReminderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Reminder findReminderByComplianceOrTaskOrSubTask(Compliance compliance, ComplianceTask complianceTask, ComplianceSubTask complianceSubTask) {
        Reminder reminder=null;
        if(compliance!=null)
            reminder=(Reminder) this.sessionFactory.getCurrentSession().createCriteria(Reminder.class)
                    .add(Restrictions.eq("compliance", compliance)).setMaxResults(1).uniqueResult();

        else if(complianceTask!=null)
            reminder=(Reminder) this.sessionFactory.getCurrentSession().createCriteria(Reminder.class)
                    .add(Restrictions.eq("complianceTask", complianceTask)).setMaxResults(1).uniqueResult();

        else if(complianceSubTask!=null)
            reminder=(Reminder) this.sessionFactory.getCurrentSession().createCriteria(Reminder.class)
                    .add(Restrictions.eq("complianceSubTask", compliance)).setMaxResults(1).uniqueResult();

        return reminder;
    }

    @Override
    public Reminder saveReminder(Reminder reminder) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(reminder);
            tx.commit();
            return reminder;
        }catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public Reminder findReminderByComplianceOrTaskOrSubTaskAndIdNot(Compliance compliance, ComplianceTask complianceTask, ComplianceSubTask complianceSubTask, Long id) {
        Reminder reminder=null;
        if(compliance!=null)
            reminder=(Reminder) this.sessionFactory.getCurrentSession().createCriteria(Reminder.class)
                    .add(Restrictions.and(Restrictions.eq("compliance", compliance),
                            Restrictions.ne("id", id))).setMaxResults(1).uniqueResult();

        else if(complianceTask!=null)
            reminder=(Reminder) this.sessionFactory.getCurrentSession().createCriteria(Reminder.class)
                    .add(Restrictions.and(Restrictions.eq("complianceTask", complianceTask),
                            Restrictions.ne("id", id))).setMaxResults(1).uniqueResult();

        else if(complianceSubTask!=null)
            reminder=(Reminder) this.sessionFactory.getCurrentSession().createCriteria(Reminder.class)
                    .add(Restrictions.and(Restrictions.eq("complianceSubTask", compliance),
                            Restrictions.ne("id", id))).setMaxResults(1).uniqueResult();

        return reminder;
    }

    @Override
    @Transactional
    public Reminder findReminderById(Long id) {
        return this.sessionFactory.getCurrentSession().get(Reminder.class,id);
    }

    @Override
    public boolean deleteReminder(Reminder reminder) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.delete(reminder);
            tx.commit();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    public Reminder updateReminder(Reminder reminder) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.update(reminder);
            tx.commit();
            return reminder;
        }catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }
}
