package com.lawzoom.complianceservice.dao.complianceTaskDao;

import com.lawzoom.complianceservice.model.complianceModel.Compliance;
import com.lawzoom.complianceservice.model.complianceTaskModel.ComplianceTask;

import java.util.List;

public interface ComplianceTaskDao {
    List<ComplianceTask> findComplianceTaskByCompliance(Compliance compliance);

    ComplianceTask fetchComplianceTaskById(Long taskId);

    ComplianceTask findTaskByComplianceAndTaskName(Compliance compliance, String taskName);

    ComplianceTask saveComplianceTask(ComplianceTask mapToSaveComplianceTask);

    ComplianceTask findTaskByComplianceAndTaskNameAndIdNot(Compliance compliance,
                                                           String taskName, Long id);

    ComplianceTask updateComplianceTask(ComplianceTask mapToUpdateComplianceTask);

    ComplianceTask fetchTaskByComplianceAndId(Compliance compliance, Long taskId);

    boolean deleteComplianceTask(ComplianceTask complianceTask);

    ComplianceTask findComplianceTaskById(Long taskId);
}
