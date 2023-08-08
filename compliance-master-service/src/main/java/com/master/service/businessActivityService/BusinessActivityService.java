package com.master.service.businessActivityService;

import com.master.dto.businessActivityDto.BusinessActivityRequest;
import com.master.response.ResponseEntity;

public interface BusinessActivityService {

    ResponseEntity fetchAllBusinessActivity();

    ResponseEntity saveBusinessActivity(BusinessActivityRequest baRequest);

    ResponseEntity updateBusinessActivity(BusinessActivityRequest baRequest);

    ResponseEntity fetchBusinessActivityById(Long businessActivityId);

    ResponseEntity deleteBusinessActivityById(Long businessActivityId);

    ResponseEntity searchBusinessActivity(String searchData);

    ResponseEntity searchBusinessActivityBySubIndustryId(Long subIndustryId);
}
