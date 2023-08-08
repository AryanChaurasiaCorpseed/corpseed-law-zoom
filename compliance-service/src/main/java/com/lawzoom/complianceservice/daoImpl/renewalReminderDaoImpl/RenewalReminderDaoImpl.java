package com.lawzoom.complianceservice.daoImpl.renewalReminderDaoImpl;

import com.lawzoom.complianceservice.dao.renewalReminderDao.RenewalReminderDao;
import com.lawzoom.complianceservice.model.complianceSubTaskModel.ComplianceSubTask;
import com.lawzoom.complianceservice.model.complianceTaskModel.ComplianceTask;
import com.lawzoom.complianceservice.model.renewalModel.RenewalReminder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RenewalReminderDaoImpl implements RenewalReminderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public RenewalReminder findRenewalReminderByComplianceTaskOrSubTask(ComplianceTask complianceTask, ComplianceSubTask complianceSubTask) {

        RenewalReminder renewalReminder=null;

        if(complianceTask!=null)
            renewalReminder= (RenewalReminder) this.sessionFactory.getCurrentSession().createCriteria(RenewalReminder.class)
                    .add(Restrictions.eq("complianceTask", complianceTask)).setMaxResults(1).uniqueResult();

        else if(complianceSubTask!=null)
            renewalReminder= (RenewalReminder) this.sessionFactory.getCurrentSession().createCriteria(RenewalReminder.class)
                    .add(Restrictions.eq("complianceSubTask", complianceSubTask)).setMaxResults(1).uniqueResult();
        return renewalReminder;
    }

    @Override
    public RenewalReminder saveRenewalReminder(RenewalReminder renewalReminder) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(renewalReminder);
            tx.commit();
            return renewalReminder;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
            // TODO: handle exception
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    public RenewalReminder updateRenewalReminder(RenewalReminder renewalReminder) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.update(renewalReminder);
            tx.commit();
            return renewalReminder;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
            // TODO: handle exception
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public RenewalReminder findRenewalReminderByComplianceTaskOrSubTaskAndIdNot(ComplianceTask complianceTask, ComplianceSubTask complianceSubTask, Long id) {
        RenewalReminder renewalReminder=null;

        System.out.println("id=="+id);

        if(complianceTask!=null)
            renewalReminder= (RenewalReminder) this.sessionFactory.getCurrentSession().createCriteria(RenewalReminder.class)
                    .add(Restrictions.and(Restrictions.eq("complianceTask", complianceTask),
                            Restrictions.ne("id", id))).setMaxResults(1).uniqueResult();

        else if(complianceSubTask!=null)
            renewalReminder= (RenewalReminder) this.sessionFactory.getCurrentSession().createCriteria(RenewalReminder.class)
                    .add(Restrictions.and(Restrictions.eq("complianceSubTask", complianceSubTask),
                            Restrictions.ne("id", id))).setMaxResults(1).uniqueResult();
        return renewalReminder;
    }

    @Override
    @Transactional
    public RenewalReminder findRenewalReminderById(Long id) {
        return this.sessionFactory.getCurrentSession().get(RenewalReminder.class,id);
    }

    @Override
    public boolean deleteRenewalReminder(RenewalReminder renewalReminder) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.delete(renewalReminder);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
            // TODO: handle exception
        }finally {
            if(session!=null)session.close();
        }
    }
}
