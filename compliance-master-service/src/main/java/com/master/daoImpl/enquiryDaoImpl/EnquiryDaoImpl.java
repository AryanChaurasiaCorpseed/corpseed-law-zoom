package com.master.daoImpl.enquiryDaoImpl;

import com.master.dao.enquiryDao.EnquiryDao;
import com.master.model.enquiryModel.Enquiry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EnquiryDaoImpl implements EnquiryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean saveEnquiry(Enquiry enquiry) {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(enquiry);
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
}
