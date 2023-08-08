package com.master.daoImpl.industryDaoImpl;

import com.master.dao.industryDao.IndustryDao;
import com.master.model.industryModel.Industry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class IndustryDaoImpl implements IndustryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Industry> fetchAllIndustry() {
		return this.sessionFactory.getCurrentSession().createCriteria(Industry.class).list();
	}

	@Override
	public boolean deleteIndustry(Industry industry) {
		Session session=null;
		Transaction tx=null;
		try {
			session=this.sessionFactory.openSession();
			tx=session.beginTransaction();
			session.delete(industry);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			return false;
		}finally {
			if(session!=null)session.close();
		}
	}

	@Override
	@Transactional
	public Industry fetchIndustryById(Long industryId) {
		return this.sessionFactory.getCurrentSession().get(Industry.class,industryId);
	}

	@Override
	@Transactional
	public Industry findIndustryByTitle(String title) {
		return (Industry) this.sessionFactory.getCurrentSession().createCriteria(Industry.class)
				.add(Restrictions.eq("title", title))
				.setMaxResults(1).uniqueResult();
	}

	@Override
	public Industry saveIndustry(Industry industry) {
		Session session=null;
		Transaction tx=null;
		try {
			session=this.sessionFactory.openSession();
			tx=session.beginTransaction();
			session.save(industry);
			tx.commit();
			return industry;
		} catch (Exception e) {
			tx.rollback();
			return null;
		}finally {
			if(session!=null)session.close();
		}
	}

	@Override
	@Transactional
	public Industry findIndustryByTitleAndIdNot(String title, Long industryId) {
		return (Industry) this.sessionFactory.getCurrentSession().createCriteria(Industry.class)
				.add(Restrictions.and(Restrictions.eq("title", title),
						Restrictions.ne("id", industryId)))
				.setMaxResults(1).uniqueResult();
	}

	@Override
	public Industry updateIndustry(Industry industry) {
		Session session=null;
		Transaction tx=null;
		try {
			session=this.sessionFactory.openSession();
			tx=session.beginTransaction();
			session.update(industry);
			tx.commit();
			return industry;
		} catch (Exception e) {
			tx.rollback();
			return null;
		}finally {
			if(session!=null)session.close();
		}
	}
}
