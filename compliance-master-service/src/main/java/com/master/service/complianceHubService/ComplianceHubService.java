package com.master.service.complianceHubService;

import com.master.dto.compliance.ComplianceMasterResponse;

import java.util.List;

public interface ComplianceHubService {
    List<ComplianceMasterResponse> findComplianceHubByBusinessActivity(String businessActivity);
}
