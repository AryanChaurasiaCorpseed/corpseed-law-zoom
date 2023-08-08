package com.lawzoom.companyservice.daoImpl.teamDaoImpl;

import com.lawzoom.companyservice.dao.teamDao.TeamDao;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.teamModel.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class TeamDaoImpl implements TeamDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Team saveTeam(Team team) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(team);
            tx.commit();
            return team;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }

    /*@Override
    @Transactional
    public Team findTeamByTeamNameOrUserAndIdNot(String teamName,User user, int id) {
        return (Team) this.sessionFactory.getCurrentSession().createCriteria(Team.class)
                .add(Restrictions.or(Restrictions.eq("teamName",teamName),
                Restrictions.eq("user",user)))
                .add(Restrictions.ne("id",id))
                .setMaxResults(1).uniqueResult();
    }*/

    @Override
    public Team updateTeam(Team team) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.update(team);
            tx.commit();
            return team;
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
    public Team fetchTeamByCompanyAndId(Company company, Long teamId) {
        return (Team) this.sessionFactory.getCurrentSession().createCriteria(Team.class)
        .add(Restrictions.and(Restrictions.eq("company",company),
                Restrictions.eq("id",teamId)))
        .setMaxResults(1).uniqueResult();
    }

    @Override
    public boolean deleteTeam(Team team) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.delete(team);
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
    public Team fetchTeamById(Long teamId) {
        return this.sessionFactory.getCurrentSession().get(Team.class,teamId);
    }

    @Transactional
    @Override
    public List<Team> findTeamByCompany(Company company) {
        return this.sessionFactory.getCurrentSession().createCriteria(Team.class)
                .add(Restrictions.eq("company",company)).list();
    }

    @Transactional
    @Override
    public Team findTeamByCompanyAndName(Company company, String teamName) {
        return (Team) this.sessionFactory.getCurrentSession().createCriteria(Team.class)
                .add(Restrictions.and(Restrictions.eq("company",company),
                        Restrictions.eq("teamName",teamName)))
                .setMaxResults(1).uniqueResult();
    }

  /*  @Override
    @Transactional
    public boolean isUserIsTeamLeader(User user) {
       if(this.sessionFactory.getCurrentSession().createCriteria(Team.class)
               .add(Restrictions.eq("user",user)).list().isEmpty())
           return false;
       else return true;
    }*/
}
