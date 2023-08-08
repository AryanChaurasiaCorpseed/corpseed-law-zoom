package com.lawzoom.complianceservice.dao.complianceMapDaoImpl;

import com.lawzoom.complianceservice.dao.complianceMapDao.ComplianceMapDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ComplianceMapDaoImpl implements ComplianceMapDao {

    @Autowired
    private SessionFactory sessionFactory;

}
