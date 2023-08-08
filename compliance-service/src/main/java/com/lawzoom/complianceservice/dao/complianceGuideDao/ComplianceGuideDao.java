package com.lawzoom.complianceservice.dao.complianceGuideDao;

import com.lawzoom.complianceservice.model.complianceGuide.ComplianceGuide;
import com.lawzoom.complianceservice.model.complianceSubTaskModel.ComplianceSubTask;
import com.lawzoom.complianceservice.model.complianceTaskModel.ComplianceTask;

import java.util.List;

public interface ComplianceGuideDao {

    List<ComplianceGuide> findTaskGuidesByTask(ComplianceTask complianceTask);

    ComplianceGuide saveComplianceGuide(ComplianceGuide complianceGuide);

    ComplianceGuide findGuideByTaskAndJurisdictionAndIdNot(ComplianceTask complianceTask,String jurisdiction, Long id);

    ComplianceGuide updateComplianceGuide(ComplianceGuide complianceGuide);

    ComplianceGuide findGuideByTaskAndId(ComplianceTask complianceTask, Long guideId);

    boolean deleteComplianceGuide(ComplianceGuide complianceGuide);

    List<ComplianceGuide> findGuidesBySubTask(ComplianceSubTask complianceSubTask);

    ComplianceGuide findGuideBySubTaskAndJurisdiction(ComplianceSubTask complianceSubTask,String jurisdiction);

    ComplianceGuide findGuideBySubTaskAndJurisdictionAndIdNot(ComplianceSubTask complianceSubTask,String jurisdiction, Long id);

    ComplianceGuide findGuideBySubTaskAndId(ComplianceSubTask complianceSubTask, Long guideId);

    ComplianceGuide findTaskGuideByTaskAndJurisdiction(ComplianceTask complianceTask, String jurisdiction);

    ComplianceGuide findComplianceGuideById(Long guideId);
}
