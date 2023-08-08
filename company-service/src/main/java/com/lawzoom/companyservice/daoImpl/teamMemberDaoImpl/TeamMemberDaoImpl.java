package com.lawzoom.companyservice.daoImpl.teamMemberDaoImpl;

import com.lawzoom.companyservice.dao.teamMemberDao.TeamMemberDao;
import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
import com.lawzoom.companyservice.model.teamModel.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TeamMemberDaoImpl implements TeamMemberDao {

    @Autowired
    private SessionFactory sessionFactory;

   /* @Override
    @Transactional
    public TeamMember findTeamMemberByUser(User user) {
        return (TeamMember) this.sessionFactory.getCurrentSession().createCriteria(TeamMember.class)
                .add(Restrictions.eq("user",user)).setMaxResults(1).uniqueResult();
    }*/

    @Override
    public TeamMember saveTeamMember(TeamMember teamMember) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.save(teamMember);
            tx.commit();
            return teamMember;
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
    public TeamMember findTeamMemberByMemberIdAndTeamAndIdNot(Long memberId,Team team, Long teamMemberId) {
        System.out.println(memberId+"\t"+teamMemberId+"\t"+team.getId());
        return (TeamMember) this.sessionFactory.getCurrentSession().createCriteria(TeamMember.class)
                .add(Restrictions.and(Restrictions.eq("memberId",memberId),
                        Restrictions.eq("team",team)))
                .add(Restrictions.ne("id",teamMemberId)).setMaxResults(1).uniqueResult();
    }

    @Override
    public TeamMember updateTeamMember(TeamMember teamMember) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.update(teamMember);
            tx.commit();
            return teamMember;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
        }finally {
            if(session!=null)session.close();
        }
    }

    @Transactional
    @Override
    public TeamMember findTeamMemberByTeamAndMemberId(Team team, Long memberId) {
        return (TeamMember) this.sessionFactory.getCurrentSession().createCriteria(TeamMember.class)
                .add(Restrictions.and(Restrictions.eq("team",team),
                        Restrictions.eq("memberId",memberId)))
                .setMaxResults(1).uniqueResult();
    }

    /*@Override
    @Transactional
    public TeamMember findTeamMemberByTeamAndId(Team team, int id) {
        return (TeamMember) this.sessionFactory.getCurrentSession().createCriteria(TeamMember.class)
                .add(Restrictions.and(Restrictions.eq("id", id),
                        Restrictions.eq("team", team)))
                .setMaxResults(1).uniqueResult();
    }*/

    @Override
    public boolean deleteTeamMember(TeamMember teamMember) {
        Session session=null;
        Transaction tx=null;
        try {
            session=this.sessionFactory.openSession();
            tx=session.beginTransaction();
            session.delete(teamMember);
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
}
