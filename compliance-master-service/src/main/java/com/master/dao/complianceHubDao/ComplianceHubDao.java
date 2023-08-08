package com.master.dao.complianceHubDao;

import com.master.model.complianceHubModel.ComplianceHub;

import java.util.List;

public interface ComplianceHubDao {
    List<ComplianceHub> findComplianceHubByBusinessActivity(String businessActivity);
}
