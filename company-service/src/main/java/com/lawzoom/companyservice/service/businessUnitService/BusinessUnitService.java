package com.lawzoom.companyservice.service.businessUnitService;

import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitRequest;
import com.lawzoom.companyservice.response.ResponseEntity;

public interface BusinessUnitService {

    ResponseEntity fetchAllBusinessUnitByGstId(Long gstId);

    ResponseEntity saveBusinessUnit(BusinessUnitRequest businessUnitRequest, Long gstId);

    ResponseEntity updateBusinessUnit(BusinessUnitRequest businessUnitRequest, Long gstId);

    ResponseEntity fetchBusinessUnit(Long businessUnitId, Long gstId);

    ResponseEntity deleteBusinessUnit(Long businessUnitId, Long gstId);

}
