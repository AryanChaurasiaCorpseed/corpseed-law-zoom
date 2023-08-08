package com.master.service.industryService;

import com.master.dto.industryDto.IndustryRequest;
import com.master.response.ResponseEntity;

public interface IndustryService {

	ResponseEntity fetchAllIndustries();

	ResponseEntity deleteIndustryById(Long industryId);

	ResponseEntity fetchIndustryById(Long industryId);

	ResponseEntity updateIndustry(IndustryRequest industryRequest);

	ResponseEntity saveIndustry(IndustryRequest industryRequest);
}
