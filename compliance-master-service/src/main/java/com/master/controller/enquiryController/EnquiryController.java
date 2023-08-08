package com.master.controller.enquiryController;

import com.master.response.ResponseEntity;
import com.master.service.enquiryService.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master/enquiry")
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    @PutMapping("/compliance/{complianceId}/{status}")
    public ResponseEntity saveComplianceEnquiry(@PathVariable("complianceId") Long complianceId,
                                                @PathVariable("status") int status){
        return this.enquiryService.saveComplianceEnquiry(complianceId,status);
    }
}
