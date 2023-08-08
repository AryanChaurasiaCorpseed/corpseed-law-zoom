package com.master.daoImpl.subIndustryDaoImpl;

import com.master.dao.subIndustryDao.SubIndustryDao;
import com.master.model.industryModel.Industry;
import com.master.model.subIndustryModel.SubIndustry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class SubIndustryDaoImpl implements SubIndustryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public SubIndustry fetchSubIndustryById(Long subIndustryId) {
		return (SubIndustry) this.sessionFactory.getCurrentSession().createCriteria(SubIndustry.class)
				.add(Restrictions.eq("id",subIndustryId)).uniqueResult();
	}

	@Override
	public boolean deleteSubIndustry(SubIndustry subIndustry) {
		Session session=null;
		Transaction tx=null;
		try {
			session=this.sessionFactory.openSession();
			tx=session.beginTransaction();
			session.delete(subIndustry);
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
	public List<SubIndustry> fetchAllSubIndustry() {
		return this.sessionFactory.getCurrentSession().createCriteria(SubIndustry.class).list();
	}


	@Override
	@Transactional
	public SubIndustry findSubIndustryByIndustryAndTitle(Industry industry, String title) {
		return (SubIndustry) this.sessionFactory.getCurrentSession().createCriteria(SubIndustry.class)
				.add(Restrictions.and(Restrictions.eq("industry", industry),
						Restrictions.eq("title", title)))
				.setMaxResults(1).uniqueResult();
	}

	@Override
	public SubIndustry saveSubIndustry(SubIndustry subIndustry) {
		Session session=null;
		Transaction tx=null;
		try {
			session=this.sessionFactory.openSession();
			tx=session.beginTransaction();
			session.save(subIndustry);
			tx.commit();
			return subIndustry;
		} catch (Exception e) {
			tx.rollback();
			return null;
		}finally {
			if(session!=null)session.close();
		}
	}

	@Override
	@Transactional
	public SubIndustry findSubIndustryByIndustryAndTitleAndIdNot(Industry industry, String title, Long subIndustryId) {
		return (SubIndustry) this.sessionFactory.getCurrentSession().createCriteria(SubIndustry.class)
				.add(Restrictions.and(Restrictions.eq("industry", industry),
						Restrictions.eq("title", title)))
				.add(Restrictions.ne("id", subIndustryId))
				.setMaxResults(1).uniqueResult();
	}

	@Override
	public SubIndustry updateSubIndustry(SubIndustry subIndustry) {
		Session session=null;
		Transaction tx=null;
		try {
			session=this.sessionFactory.openSession();
			tx=session.beginTransaction();
			session.update(subIndustry);
			tx.commit();
			return subIndustry;
		} catch (Exception e) {
			tx.rollback();
			return null;
		}finally {
			if(session!=null)session.close();
		}
	}

	@Transactional
	@Override
	public List<SubIndustry> fetchSubIndustryByIndustry(Industry industry) {
		return this.sessionFactory.getCurrentSession().createCriteria(SubIndustry.class)
				.add(Restrictions.eq("industry",industry)).list();
	}
}
