package com.lawzoom.complianceservice.dao.complianceSubTaskDao;

import com.lawzoom.complianceservice.model.complianceSubTaskModel.ComplianceSubTask;
import com.lawzoom.complianceservice.model.complianceTaskModel.ComplianceTask;

import java.util.List;

public interface ComplianceSubTaskDao {
    List<ComplianceSubTask> findComplianceSubTaskByComplianceTask(ComplianceTask complianceTask);

    ComplianceSubTask findSubTaskByComplianceTaskAndName(ComplianceTask complianceTask, String subTaskName);

    ComplianceSubTask saveComplianceSubTask(ComplianceSubTask mapToSaveComplianceSubTask);

    ComplianceSubTask findSubTaskByComplianceAndNameAndIdNot(ComplianceTask complianceTask, String subTaskName, Long id);

    ComplianceSubTask updateComplianceSubTask(ComplianceSubTask mapToUpdateComplianceSubTask);

    ComplianceSubTask findSubTaskByTaskAndId(ComplianceTask complianceTask, Long subTaskId);

    boolean deleteComplianceSubTask(ComplianceSubTask complianceSubTask);

    ComplianceSubTask findComplianceSubTaskById(Long subTaskId);
}
