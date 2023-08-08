package com.lawzoom.complianceservice.daoImpl.complianceDaoImpl;

import com.lawzoom.complianceservice.dao.complianceDao.ComplianceDao;
import com.lawzoom.complianceservice.dto.complianceTaskDto.ManageComplianceTaskResponse;
import com.lawzoom.complianceservice.dto.setComplianceMapDto.SetComplianceMapResponse;
import com.lawzoom.complianceservice.model.complianceModel.Compliance;
import com.lawzoom.complianceservice.model.complianceSubTaskModel.ComplianceSubTask;
import com.lawzoom.complianceservice.model.complianceTaskModel.ComplianceTask;
import com.lawzoom.complianceservice.response.ResponseEntity;
import com.lawzoom.complianceservice.utility.CommonUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ComplianceDaoImpl implements ComplianceDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Compliance> fetchAllComplianceByCompanyId(Long companyId) {
        return this.sessionFactory.getCurrentSession().createCriteria(Compliance.class)
                .add(Restrictions.eq("companyId", companyId)).list();
    }

    @Override
    public Compliance saveCompliance(Compliance compliance) {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(compliance);
            tx.commit();
            return compliance;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Compliance updateCompliance(Compliance compliance) {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(compliance);
            tx.commit();
            return compliance;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    @Transactional
    public Compliance findComplianceByCompanyAndId(Long companyId, Long complianceId) {
        return (Compliance) this.sessionFactory.getCurrentSession().createCriteria(Compliance.class)
                .add(Restrictions.and(Restrictions.eq("companyId", companyId),
                        Restrictions.eq("id", companyId)))
                .setMaxResults(1).uniqueResult();
    }

    @Override
    public boolean deleteCompliance(Compliance compliance) {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.delete(compliance);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void saveAllCompliances(List<Compliance> complianceList) {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            for (Compliance compliance : complianceList) {
                System.out.println(compliance);
                session.save(compliance);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    /*@Override
    @Transactional
    public long countCompliances(Long businessUnitId,Long companyId) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(
                "select count(*) from Compliance c where c.companyId=:companyId or " +
                        "c.businessUnitId=:businessUnitId");
        query.setParameter("companyId", companyId);
        query.setParameter("businessUnitId", businessUnitId);
        Long count = (Long)query.uniqueResult();
        return count;
    }*/

    @Override
    @Transactional
    public List<Compliance> findCompliancesByBusinessUnitId(Long businessUnitId) {
        return this.sessionFactory.getCurrentSession().createCriteria(Compliance.class)
                .add(Restrictions.eq("businessUnitId", businessUnitId)).list();
    }

    @Override
    @Transactional
    public Compliance findComplianceById(Long complianceId) {
        return this.sessionFactory.getCurrentSession().get(Compliance.class, complianceId);
    }

    @Override
    @Transactional
    public List<ManageComplianceTaskResponse> fetchManageCompliancesByUserId(Long userId) {
        Session session = null;
        Transaction tx = null;
        List<ManageComplianceTaskResponse> result = new ArrayList<>();
        /*String query = "Select c.name,b.city,g.state_jurisdiction,b.business_activity,t.id," +
                "t.task_name,t.start_date,t.due_date,t.completed_date,t.status,t.reporter_id," +
                "(select concat(u.first_name,concat(' ',u.last_name)) from user u where u.id=t.reporter_id)" +
                ",t.assignee_id,(select concat(u.first_name,concat(' ',u.last_name)) from user u where u.id=t.assignee_id)," +
                "t.criticality from company c inner join gst g on c.id=g.company_id " +
                "inner join business_unit b on g.id=b.gst_id inner join compliance cm on " +
                "b.id=cm.business_unit_id inner join compliance_task t on " +
                "cm.id=t.compliance_id where c.super_user_id=" + userId;*/

        /*result.add(ManageComplianceTaskResponse.builder().companyName(convertToString(row[0]))
                .businessUnitName(convertToString(row[1]) + "," + hashAfter(convertToString(row[2])))
                .businessActivity(convertToString(row[3])).taskId(convertToLong(row[4].toString()))
                .taskName(convertToString(row[5])).startDate(convertToString(row[6]))
                .dueDate(convertToString(row[7])).completedDate(convertToString(row[8]))
                .status(convertToString(row[9])).reporterId(convertToLong(row[10]))
                .reporterName(convertToString(row[11])).assigneeId(convertToLong(row[12]))
                .assigneeName(convertToString(row[13])).criticality(convertToString(row[14]))
                .expectedProgress(CommonUtil.expectedProgress(row[6], row[7]))
                .actualProgress(actualProgress(row[4], row[8]))
                .totalDays(CommonUtil.totalDays(row[6], row[7])).dayConsumed(CommonUtil.dayConsumed(row[6], row[8]))
                .build());*/


        String query="select cmp.name, ct.id,ct.task_name,ct.start_date," +
                "ct.due_date,ct.completed_date,ct.status,ct.reporter_id," +
                "(select concat(u.first_name,concat(' ',u.last_name)) from user u where u.id=ct.reporter_id) as reporter_name," +
                "ct.assignee_id,(select concat(u.first_name,concat(' ',u.last_name)) from user u where u.id=ct.assignee_id) as assignee_name," +
                "ct.criticality from compliance_task ct " +
                "inner join compliance c ON ct.compliance_id = c.id " +
                "inner join company cmp ON c.company_id = cmp.id " +
                "inner join user u ON cmp.user_id = u.id " +
                "where u.id = "+userId;

        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();

            Query sqlQuery = session.createSQLQuery(query);
            List<Object[]> objectList = sqlQuery.list();
            for (Object[] row : objectList) {
                result.add(ManageComplianceTaskResponse.builder().companyName(convertToString(row[0]))
                        .taskId(convertToLong(row[1].toString()))
                        .taskName(convertToString(row[2])).startDate(convertToString(row[3]))
                        .dueDate(convertToString(row[4])).completedDate(convertToString(row[5]))
                        .status(convertToString(row[6])).reporterId(convertToLong(row[7]))
                        .reporterName(convertToString(row[8])).assigneeId(convertToLong(row[9]))
                        .assigneeName(convertToString(row[10])).criticality(convertToString(row[11]))
                        .expectedProgress(CommonUtil.expectedProgress(row[3], row[4]))
                        .actualProgress(actualProgress(convertToLong(row[1]),convertToDate(row[5])))
                        .totalDays(CommonUtil.totalDays(row[3], row[4]))
                        .dayConsumed(CommonUtil.dayConsumed(row[3], row[5]))
                        .build());
            }

            query="select cmp.name,bu.city,gst.state_jurisdiction,bu.business_activity," +
                    "ct.id,ct.task_name,ct.start_date,ct.due_date,ct.completed_date,ct.status,ct.reporter_id," +
                    "(select concat(u.first_name,concat(' ',u.last_name)) from user u where u.id=ct.reporter_id) as reporter_name," +
                    "ct.assignee_id,(select concat(u.first_name,concat(' ',u.last_name)) from user u where u.id=ct.assignee_id) as assignee_name," +
                    "ct.criticality from compliance_task ct " +
                    "inner join compliance c ON ct.compliance_id = c.id " +
                    "inner join business_unit bu ON c.business_unit_id = bu.id " +
                    "inner join gst gst ON bu.gst_id = gst.id " +
                    "inner join company cmp ON gst.company_id = cmp.id " +
                    "inner join user u ON cmp.user_id = u.id " +
                    "where u.id = "+userId;

            sqlQuery = session.createSQLQuery(query);
            objectList = sqlQuery.list();
            for (Object[] row : objectList) {
                result.add(ManageComplianceTaskResponse.builder().companyName(convertToString(row[0]))
                        .businessUnitName(convertToString(row[1]) + "," + hashAfter(convertToString(row[2])))
                        .businessActivity(convertToString(row[3])).taskId(convertToLong(row[4].toString()))
                        .taskName(convertToString(row[5])).startDate(convertToString(row[6]))
                        .dueDate(convertToString(row[7])).completedDate(convertToString(row[8]))
                        .status(convertToString(row[9])).reporterId(convertToLong(row[10]))
                        .reporterName(convertToString(row[11])).assigneeId(convertToLong(row[12]))
                        .assigneeName(convertToString(row[13])).criticality(convertToString(row[14]))
                        .expectedProgress(CommonUtil.expectedProgress(row[6], row[7]))
                        .actualProgress(actualProgress(convertToLong(row[4]),convertToDate(row[8])))
                        .totalDays(CommonUtil.totalDays(row[6], row[7])).dayConsumed(CommonUtil.dayConsumed(row[6], row[8]))
                        .build());
            }
            tx.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            if (session != null) session.close();
        }
    }

    @Transactional
    public int actualProgress(Long taskId, Date completedDate) {

        if (taskId == 0) return 0;

        if (completedDate != null) return 100;

        List<ComplianceSubTask> subTasks = this.sessionFactory.getCurrentSession().createCriteria(ComplianceSubTask.class)
                .add(Restrictions.eq("complianceTask", ComplianceTask.builder().id(taskId).build()))
                .list();

        System.out.println("subTasks size==" + subTasks.size());
        if (subTasks.isEmpty()) return 0;

        List<ComplianceSubTask> completedSubTask = subTasks.stream().filter(t -> t.getCompletedDate() != null).collect(Collectors.toList());
//        System.out.println("Actual Progress=="+(100/ subTasks.size())*completedSubTask.size());
        System.out.println("completedSubTask size==" + completedSubTask.size());
        int progress=(100 / subTasks.size()) * completedSubTask.size();
        if(progress>98)progress=100;

        return progress;
    }

    private String hashAfter(String convertToString) {
        if (convertToString == null)
            return null;
        return convertToString.substring(convertToString.indexOf("#") + 1);
    }


    @Override
    @Transactional
    public Compliance findComplianceByBusinessUnitAndId(Long businessUnitId, Long complianceId) {
        return (Compliance) this.sessionFactory.getCurrentSession().createCriteria(Compliance.class)
                .add(Restrictions.and(Restrictions.eq("id", complianceId),
                        Restrictions.eq("businessUnitId", businessUnitId)))
                .setMaxResults(1).uniqueResult();
    }


    @Transactional
    @Override
    public List<Compliance> fetchComplianceCustom(Long companyId, Long businessId) {
        return this.sessionFactory.getCurrentSession().createCriteria(Compliance.class)
                .add(Restrictions.or(Restrictions.eq("companyId", companyId),
                        Restrictions.and(Restrictions.ne("businessUnitId", 0L),
                                Restrictions.eq("businessUnitId", businessId))))
                .list();
    }

    @Transactional
    @Override
    public Compliance fetchComplianceById(Long complianceId) {
        return this.sessionFactory.getCurrentSession().get(Compliance.class, complianceId);
    }

    @Override
    public List<SetComplianceMapResponse> fetchAllSetComplianceMap(Long userId) {
        Session session = null;
        Transaction tx = null;
        List<SetComplianceMapResponse> result = new ArrayList<>();
        /*String query = "Select company.id as company_id,company.name,business_unit.id,business_unit.city," +
                "gst.state_jurisdiction,business_unit.business_activity,business_unit.created_at,team.team_name" +
                ",(select count(co.id) from compliance co where co.company_id=company.id or co.business_unit_id=business_unit.id)" +
                " as compliance_count from company left join gst on company.id=gst.company_id" +
                " left join business_unit on gst.id=business_unit.gst_id left join team on company.id=team.company_id " +
                "where company.user_id=" + userId;*/

        String query="select cmp2.id, cmp2.name, count(1) from compliance c " +
                "inner join company cmp2 ON c.company_id = cmp2.id " +
                "inner join user u ON cmp2.user_id = u.id " +
                "where u.id = 1 group by cmp2.id";


        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();

            Query sqlQuery = session.createSQLQuery(query);
            List<Object[]> objectList = sqlQuery.list();
            for (Object[] row : objectList) {
                result.add(SetComplianceMapResponse.builder().companyId(convertToLong(row[0]))
                        .companyName(convertToString(row[1])).complianceCount(convertToLong(row[2])).build());

                String query1="select cmp.id as company_id,cmp.name as company_name,bu.id," +
                        "bu.city,gst.state_jurisdiction,bu.business_activity,count(1) from compliance c " +
                        "inner join business_unit bu ON c.business_unit_id = bu.id " +
                        "inner join gst gst ON bu.gst_id = gst.id " +
                        "inner join company cmp ON gst.company_id = cmp.id " +
                        "where cmp.id = "+convertToLong(row[0])+" group by bu.id";

                Query sqlQuery1 = session.createSQLQuery(query1);
                List<Object[]> objectList1 = sqlQuery1.list();
                for (Object[] row1 : objectList1) {
                    result.add(SetComplianceMapResponse.builder().companyId(convertToLong(row1[0]))
                            .companyName(convertToString(row1[1])).businessUnitId(convertToLong(row1[2]))
                            .businessUnitCity(convertToString(row1[3])).stateJurisdiction(convertToString(row1[4]))
                            .businessActivity(convertToString(row1[5])).complianceCount(convertToLong(row1[6])).build());
                }

                /*result.add(SetComplianceMapResponse.builder().companyId(convertToLong(row[0]))
                        .companyName(convertToString(row[1])).businessUnitId(convertToLong(row[2]))
                        .businessUnitCity(convertToString(row[3])).stateJurisdiction(convertToString(row[4]))
                        .businessActivity(convertToString(row[5])).createdAt(convertToDate(row[6]))
                        .teamName(convertToString(row[7])).complianceCount(convertToLong(row[8])).build());*/
            }
            tx.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            if (session != null) session.close();
        }
    }

    private Date convertToDate(Object o) {
        if (o == null)
            return null;
        return CommonUtil.parseToDate(o);
    }

    private String convertToString(Object o) {
        if (o == null)
            return null;
        return o.toString();
    }

    private Long convertToLong(Object o) {
        if (o == null)
            return 0L;
        return Long.parseLong(o.toString());
    }
}
