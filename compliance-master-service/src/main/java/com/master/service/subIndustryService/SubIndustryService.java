package com.master.service.subIndustryService;

import com.master.dto.subIndustryDto.SubIndustryRequest;
import com.master.response.ResponseEntity;

public interface SubIndustryService {

    ResponseEntity deleteSubIndustryById(Long subIndustryId);

    ResponseEntity fetchSubIndustryById(Long subIndustryId);

    ResponseEntity updateSubIndustry(SubIndustryRequest subIndustryRequest);

    ResponseEntity saveSubIndustry(SubIndustryRequest subIndustryRequest);

    ResponseEntity fetchAllSubIndustry();

    ResponseEntity fetchSubIndustryByIndustry(Long industryId);
}
