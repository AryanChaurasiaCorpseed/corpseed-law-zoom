package com.lawzoom.complianceservice.dao.legalDao;

import com.lawzoom.complianceservice.model.complianceGuide.ComplianceGuide;
import com.lawzoom.complianceservice.model.legalGuideModel.LegalGuide;

import java.util.List;

public interface LegalDao {
    List<LegalGuide> findLegalGuidesByComplianceGuide(ComplianceGuide complianceGuide);

    LegalGuide findLegalGuideByComplianceGuideAndTitle(ComplianceGuide complianceGuide, String title);

    LegalGuide saveLegalDao(LegalGuide mapToSaveLegalGuide);

    LegalGuide findLegalGuideByComplianceGuideAndTitleAndIdNot(ComplianceGuide complianceGuide, String title, Long id);

    LegalGuide updtaeLegalDao(LegalGuide mapToUpdateLegalGuide);

    LegalGuide findLegalGuideByComplianceGuideAndId(ComplianceGuide complianceGuide, Long legalId);

    boolean deleteLegalGuide(LegalGuide legalGuide);
}
