package com.lawzoom.companyservice.service.gstService;

import com.lawzoom.companyservice.dto.gstDto.GstRequest;
import com.lawzoom.companyservice.response.ResponseEntity;

public interface GstService {

    ResponseEntity fetchAllGst(Long companyId);

    ResponseEntity updateGst(GstRequest gstRequest, Long companyId);

    ResponseEntity saveGst(GstRequest gstRequest, Long companyId);

    ResponseEntity fetchGstByGstIdAndCompanyId(Long gstId, Long companyId);

    ResponseEntity deleteGstByIdAndCompanyId(Long gstId, Long companyId);

    ResponseEntity findGstByNumberAndCompanyId(String gstNumber, Long companyId);
}
