package com.lawzoom.complianceservice.daoImpl.documentDaoImpl;

import com.lawzoom.complianceservice.dao.documentDao.DocumentDao;
import com.lawzoom.complianceservice.model.complianceModel.Compliance;
import com.lawzoom.complianceservice.model.complianceSubTaskModel.ComplianceSubTask;
import com.lawzoom.complianceservice.model.complianceTaskModel.ComplianceTask;
import com.lawzoom.complianceservice.model.documentModel.Document;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DocumentDaoImpl implements DocumentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Document> findAllDocumentsByCompliance(Compliance compliance) {
        return  this.sessionFactory.getCurrentSession().createCriteria(Document.class)
                .add(Restrictions.eq("compliance",compliance)).list();
    }

    @Override
    public Document saveDocument(Document document) {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(document);
            tx.commit();
            return document;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public Document findDocumentById(Long id) {
        return this.sessionFactory.getCurrentSession().get(Document.class,id);
    }

    @Override
    public Document updateDocument(Document document) {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.update(document);
            tx.commit();
            return document;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public Document findDocumentByComplianceAndId(Compliance compliance, Long id) {
        return (Document) this.sessionFactory.getCurrentSession().createCriteria(Document.class)
                .add(Restrictions.and(Restrictions.eq("compliance",compliance),
                        Restrictions.eq("id",id)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public boolean deleteDocument(Document document) {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.delete(document);
            tx.commit();
            return true;
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Override
    @Transactional
    public Document findDocumentByComplianceTaskAndId(ComplianceTask complianceTask, Long id) {
        return (Document) this.sessionFactory.getCurrentSession().createCriteria(Document.class)
                .add(Restrictions.and(Restrictions.eq("complianceTask",complianceTask),
                        Restrictions.eq("id",id)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    @Transactional
    public List<Document> findAllDocumentsByComplianceTask(ComplianceTask complianceTask) {
        return  this.sessionFactory.getCurrentSession().createCriteria(Document.class)
                .add(Restrictions.eq("complianceTask",complianceTask)).list();
    }

    @Override
    @Transactional
    public List<Document> findAllDocumentsByComplianceSubTask(ComplianceSubTask complianceSubTask) {
        return  this.sessionFactory.getCurrentSession().createCriteria(Document.class)
                .add(Restrictions.eq("complianceSubTask",complianceSubTask)).list();
    }

    @Override
    @Transactional
    public Document findDocumentByComplianceSubTaskAndId(ComplianceSubTask complianceSubTask, Long id) {
        return (Document) this.sessionFactory.getCurrentSession().createCriteria(Document.class)
                .add(Restrictions.and(Restrictions.eq("complianceSubTask",complianceSubTask),
                        Restrictions.eq("id",id)))
                .setMaxResults(1).uniqueResult();
    }
}
