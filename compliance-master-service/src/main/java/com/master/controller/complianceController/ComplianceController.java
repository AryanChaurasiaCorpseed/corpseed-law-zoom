package com.master.controller.complianceController;

import com.master.dto.compliance.ComplianceMasterResponse;
import com.master.service.complianceHubService.ComplianceHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/master/compliance")
public class ComplianceController {

    @Autowired
    private ComplianceHubService complianceHubService;

    @GetMapping("/by-activity")
    public List<ComplianceMasterResponse> complianceByBusinessActivity(@RequestParam("businessActivity") String businessActivity){
        System.out.println("compliance master fetch compliance called.........."+businessActivity);
        return this.complianceHubService.findComplianceHubByBusinessActivity(businessActivity);
    }
}
