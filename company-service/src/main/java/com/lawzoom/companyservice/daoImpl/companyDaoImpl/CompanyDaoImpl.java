package com.lawzoom.companyservice.daoImpl.companyDaoImpl;

import com.lawzoom.companyservice.dao.companyDao.CompanyDao;
import com.lawzoom.companyservice.model.companyModel.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    @Autowired
    private SessionFactory sessionFactory;

   @Override
    @Transactional
    public Company findCompanyByUserIdAndNameOrRegNoOrCIN(Long userId, String companyName, String regNo, String companyCINNumber) {
        return (Company) this.sessionFactory.getCurrentSession().createCriteria(Company.class)
                .add(Restrictions.eq("userId",userId))
                .add(Restrictions.disjunction()
                        .add(Restrictions.eq("name", companyName))
                        .add(Restrictions.eq("cinNumber", companyCINNumber))
                        .add(Restrictions.eq("registrationNumber",regNo))
                )
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public Company saveCompany(Company company) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            System.out.println(company);
            session.save(company);
            tx.commit();
            return company;
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
    public List<Company> fetchCompaniesByUserAndNameOrCIN(Long userId, String data) {
        return this.sessionFactory.getCurrentSession().createCriteria(Company.class)
                .add(Restrictions.eq("userId", userId))
                .add(Restrictions.or(Restrictions.like("name", data, MatchMode.ANYWHERE),
                        Restrictions.like("cinNumber", data,MatchMode.ANYWHERE))).list();
    }
/*
    @Override
    public boolean deleteCompany(Company company) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.delete(company);
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




    @Transactional
    @Override
    public Company findFirstCompanyByUser(User user) {
        return (Company) this.sessionFactory.getCurrentSession().createCriteria(Company.class)
                .add(Restrictions.eq("user",user))
                .setMaxResults(1).uniqueResult();
    }*/

    @Override
    @Transactional
    public Company findCompanyByUserIdAndCompanyId(Long userId, Long companyId) {
        return (Company) this.sessionFactory.getCurrentSession().createCriteria(Company.class)
                .add(Restrictions.and(Restrictions.eq("userId", userId),
                        Restrictions.eq("id", companyId)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public Company updateCompany(Company company) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.update(company);
            tx.commit();
            return company;
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
    public Company findCompanyById(Long companyId) {
        return this.sessionFactory.getCurrentSession().get(Company.class,companyId);
    }

    @Override
    @Transactional
    public Company findCompanyByUserIdAndNameOrRegNoOrCINAndCompanyIdNot(Long userId,
                 String companyName, String regNo, String companyCINNumber, Long companyId) {
        return (Company) this.sessionFactory.getCurrentSession().createCriteria(Company.class)
                .add(Restrictions.and(Restrictions.eq("userId",userId),
                        Restrictions.ne("id",companyId)))
                .add(Restrictions.disjunction()
                        .add(Restrictions.eq("name", companyName))
                        .add(Restrictions.eq("cinNumber", companyCINNumber))
                        .add(Restrictions.eq("registrationNumber",regNo))
                )
                .setMaxResults(1).uniqueResult();
    }

    @Transactional
    @Override
    public List<Company> findCompanyByUserId(Long userId) {
        return this.sessionFactory.getCurrentSession().createCriteria(Company.class)
                .add(Restrictions.eq("userId",userId)).list();
    }

}
