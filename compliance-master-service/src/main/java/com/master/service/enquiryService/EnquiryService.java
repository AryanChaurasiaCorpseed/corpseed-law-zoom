package com.master.service.enquiryService;

import com.master.response.ResponseEntity;

public interface EnquiryService {
   ResponseEntity saveComplianceEnquiry(Long complianceId, int status);
}
