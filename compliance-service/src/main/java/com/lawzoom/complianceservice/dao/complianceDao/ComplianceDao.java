package com.lawzoom.complianceservice.dao.complianceDao;

import com.lawzoom.complianceservice.dto.complianceTaskDto.ManageComplianceTaskResponse;
import com.lawzoom.complianceservice.dto.setComplianceMapDto.SetComplianceMapResponse;
import com.lawzoom.complianceservice.model.complianceModel.Compliance;
import com.lawzoom.complianceservice.response.ResponseEntity;

import java.util.List;

public interface ComplianceDao {
    List<Compliance> fetchAllComplianceByCompanyId(Long companyId);

    Compliance saveCompliance(Compliance mapToSaveCompliance);

    Compliance updateCompliance(Compliance mapToUpdateCompliance);

    Compliance findComplianceByCompanyAndId(Long companyId, Long complianceId);

    boolean deleteCompliance(Compliance compliance);

    void saveAllCompliances(List<Compliance> complianceList);

    List<Compliance> findCompliancesByBusinessUnitId(Long businessUnitId);

    Compliance findComplianceById(Long complianceId);

    Compliance findComplianceByBusinessUnitAndId(Long businessUnitId, Long complianceId);

    List<Compliance> fetchComplianceCustom(Long companyId, Long businessId);

    Compliance fetchComplianceById(Long complianceId);

    List<SetComplianceMapResponse> fetchAllSetComplianceMap(Long userId);

    List<ManageComplianceTaskResponse> fetchManageCompliancesByUserId(Long userId);
}
