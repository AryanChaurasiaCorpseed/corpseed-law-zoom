package com.lawzoom.companyservice.feignclient;

import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitResponse;
import com.lawzoom.companyservice.dto.companyDto.CompanyResponse;
import com.lawzoom.companyservice.response.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "COMPLIANCE-SERVICE")
public interface ComplianceMap {

    @PostMapping("/compliance/map/company")
    ResponseEntity<?> mapCompanyCompliances(@RequestBody CompanyResponse companyResponse);

    @PostMapping("/compliance/map/business")
    ResponseEntity<?> mapBusinessCompliances(@RequestBody BusinessUnitResponse businessUnitResponse);
}
