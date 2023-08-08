package com.master.serviceImpl.complianceHubServiceImpl;

import com.master.dao.complianceHubDao.ComplianceHubDao;
import com.master.dto.compliance.ComplianceMasterResponse;
import com.master.model.complianceHubModel.ComplianceHub;
import com.master.service.complianceHubService.ComplianceHubService;
import com.master.util.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplianceHubServiceImpl implements ComplianceHubService {

    @Autowired
    private ComplianceHubDao complianceHubDao;

    @Autowired
    private ResponseMapper responseMapper;

    @Override
    public List<ComplianceMasterResponse> findComplianceHubByBusinessActivity(String businessActivity) {
        return this.complianceHubDao.findComplianceHubByBusinessActivity(businessActivity)
        .stream().map(this::mapToComplianceMaster).collect(Collectors.toList());
    }

    private ComplianceMasterResponse mapToComplianceMaster(ComplianceHub complianceHub) {
        return this.responseMapper.mapToComplianceMasterResponse(complianceHub);
    }
}
