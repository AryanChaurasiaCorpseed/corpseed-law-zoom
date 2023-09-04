package com.master.daoImpl.businessActivityDaoImpl;

import com.master.dao.businessActivityDao.BusinessActivityDao;
import com.master.model.businessActivityModel.BusinessActivity;
import com.master.model.subIndustryModel.SubIndustry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class BusinessActivityDaoImpl implements BusinessActivityDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<BusinessActivity> fetchAllBusinessActivity() {
		return this.sessionFactory.getCurrentSession().createCriteria(BusinessActivity.class).list();
	}

	@Override
	@Transactional
	public BusinessActivity findBusinessActivityBySubIndustryAndTitle(SubIndustry subIndustry, String title) {
		return (BusinessActivity) this.sessionFactory.getCurrentSession().createCriteria(BusinessActivity.class)
				.add(Restrictions.and(Restrictions.eq("subIndustry", subIndustry),
						Restrictions.eq("title", title)))
				.setMaxResults(1).uniqueResult();
	}

	@Override
	public BusinessActivity saveBusinessActivity(BusinessActivity businessActivity) {
		Session session=null;
		Transaction tx=null;
		try {
			session=this.sessionFactory.openSession();
			tx=session.beginTransaction();
			System.out.println(businessActivity);
			session.save(businessActivity);
			tx.commit();
			return businessActivity;
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
	public BusinessActivity findBusinessActivityBySubIndustryAndTitleAndIdNot(SubIndustry subIndustry,
																			  String title, Long businessActivityId) {
		return (BusinessActivity) this.sessionFactory.getCurrentSession().createCriteria(BusinessActivity.class)
				.add(Restrictions.and(Restrictions.eq("subIndustry", subIndustry),
						Restrictions.eq("title", title)))
				.add(Restrictions.ne("id", businessActivityId))
				.setMaxResults(1).uniqueResult();
	}

	@Override
	public BusinessActivity updateBusinessActivity(BusinessActivity businessActivity) {
		Session session=null;
		Transaction tx=null;
		try {
			session=this.sessionFactory.openSession();
			tx=session.beginTransaction();
			session.update(businessActivity);
			tx.commit();
			return businessActivity;
		} catch (Exception e) {
			tx.rollback();
			return null;
		}finally {
			if(session!=null)session.close();
		}
	}

	@Override
	@Transactional
	public BusinessActivity fetchBusinessActivityById(Long businessActivityId) {
		try {
			BusinessActivity businessActivity=(BusinessActivity) this.sessionFactory.getCurrentSession().createCriteria(BusinessActivity.class)
					.add(Restrictions.eq("id",businessActivityId));
//			System.out.println("businessActivity=="+businessActivity);
			return businessActivity;
//			return this.sessionFactory.getCurrentSession().get(BusinessActivity.class, id);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override̥
	public boolean deleteBusinessActivity(BusinessActivity businessActivity) {
		Session session=null;
		Transaction tx=null;
		try {
			session=this.sessionFactory.openSession();
			tx=session.beginTransaction();
			session.delete(businessActivity);
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
	public List<BusinessActivity> findBusinessActivityBySubIndustry(SubIndustry subIndustry) {
		return this.sessionFactory.getCurrentSession().createCriteria(BusinessActivity.class)
				.add(Restrictions.eq("subIndustry", subIndustry)).list();
	}

	@Override
	@Transactional
	public List<BusinessActivity> findBusinessActivityContains(String searchData) {
		return this.sessionFactory.getCurrentSession().createCriteria(BusinessActivity.class)
				.add(Restrictions.like("title", searchData, MatchMode.ANYWHERE))
				.list();
	}
}
