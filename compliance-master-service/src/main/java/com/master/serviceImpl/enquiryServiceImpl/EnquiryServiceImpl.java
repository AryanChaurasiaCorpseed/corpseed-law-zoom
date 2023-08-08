package com.master.serviceImpl.enquiryServiceImpl;

import com.master.dao.enquiryDao.EnquiryDao;
import com.master.response.ResponseEntity;
import com.master.service.enquiryService.EnquiryService;
import com.master.util.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    @Autowired
    private EnquiryDao enquiryDao;

    @Autowired
    private ResponseMapper responseMapper;

    @Override
    public ResponseEntity saveComplianceEnquiry(Long complianceId, int status) {
       /* Compliance compliance=this.complianceDao.findComplianceById(complianceId);
        if(compliance==null)
            return new ResponseEntity().badRequest("Compliance Not Found !!");
        compliance.setWorkStatus(status);
        Compliance updateCompliance = this.complianceDao.updateCompliance(compliance);
        if(status==1)
            this.enquiryDao.saveEnquiry(this.responseMapper.mapSaveEnquiry(compliance));

        if(updateCompliance==null)
            return new ResponseEntity().internalServerError();*/

        return new ResponseEntity().ok();
    }
}
