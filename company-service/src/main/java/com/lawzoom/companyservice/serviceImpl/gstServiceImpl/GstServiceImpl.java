package com.lawzoom.companyservice.serviceImpl.gstServiceImpl;

import com.lawzoom.companyservice.dao.companyDao.CompanyDao;
import com.lawzoom.companyservice.dao.gstDao.GstDao;
import com.lawzoom.companyservice.dto.gstDto.GstRequest;
import com.lawzoom.companyservice.dto.gstDto.GstResponse;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.gstModel.Gst;
import com.lawzoom.companyservice.response.ResponseEntity;
import com.lawzoom.companyservice.service.gstService.GstService;
import com.lawzoom.companyservice.utility.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GstServiceImpl implements GstService {

    @Autowired
    private GstDao gstDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private ResponseMapper responseMapper;

    @Override
    public ResponseEntity fetchAllGst(Long companyId) {
        Company company=this.companyDao.findCompanyById(companyId);
        if(company==null)
            return new ResponseEntity().badRequest("Company Not Found !!");

        return new ResponseEntity().ok(company.getGstList().stream().map(g->mapToGstResponse(g,company)));
    }

    private GstResponse mapToGstResponse(Gst gst,Company company) {
        return this.responseMapper.mapToGstResponse(gst,company);
    }

    @Override
    public ResponseEntity updateGst(GstRequest gstRequest, Long companyId) {
        Company company=this.companyDao.findCompanyById(companyId);
        if(company==null)
            return new ResponseEntity().badRequest("Company Not Found !!");

        Gst findGst=this.gstDao.findGstByCompanyAndGstNumberAndIdNot(company,gstRequest.getGstNumber(),gstRequest.getId());
        if(findGst!=null)
            return new ResponseEntity().badRequest("Gst Number already exist !!");

        Gst updateGst=this.gstDao.updateGst(this.responseMapper.mapToUpdateGst(gstRequest,company));

        if(updateGst==null)
            return new ResponseEntity().internalServerError();

        return new ResponseEntity().ok();
    }

    @Override
    public ResponseEntity saveGst(GstRequest gstRequest, Long companyId) {
        Company company=this.companyDao.findCompanyById(companyId);
        if(company==null)
            return new ResponseEntity().badRequest("Company Not Found !!");

        Gst findGst=this.gstDao.findGstByGstNumber(gstRequest.getGstNumber());
        if(findGst!=null)
            return new ResponseEntity().badRequest("Gst Number already exist !!");

        Gst saveGst=this.gstDao.saveGst(this.responseMapper.mapToSaveGst(gstRequest,company));

        if(saveGst==null)
            return new ResponseEntity().internalServerError();

        return new ResponseEntity().ok();
    }

    @Override
    public ResponseEntity fetchGstByGstIdAndCompanyId(Long gstId, Long companyId) {
        Company company=this.companyDao.findCompanyById(companyId);
        if(company==null)
            return new ResponseEntity().badRequest("Company Not Found !!");

        Gst gst=this.gstDao.findGstByCompanyAndId(company,gstId);
        if(gst==null)
            return new ResponseEntity().badRequest("Gst Not Found !!");

        return new ResponseEntity().ok(mapToGstResponse(gst,company));
    }

    @Override
    public ResponseEntity deleteGstByIdAndCompanyId(Long gstId, Long companyId) {
        Company company=this.companyDao.findCompanyById(companyId);
        if(company==null)
            return new ResponseEntity().badRequest("Company Not Found !!");

        Gst gst=this.gstDao.findGstByCompanyAndId(company,gstId);
        if(gst==null)
            return new ResponseEntity().badRequest("Gst Not Found !!");

        boolean deleteGst=this.gstDao.deleteGst(gst);
        if(!deleteGst)
            return new ResponseEntity().internalServerError();

        return new ResponseEntity().ok();
    }

    @Override
    public ResponseEntity findGstByNumberAndCompanyId(String gstNumber, Long companyId) {
        Company company=this.companyDao.findCompanyById(companyId);
        if(company==null)
            return new ResponseEntity().badRequest("Company Not Found !!");

        Gst findGst = this.gstDao.findGstByCompanyAndGstNumber(company, gstNumber);
        if(findGst==null)
            return new ResponseEntity().ok();

        return new ResponseEntity().badRequest("Gst Number Already Registered !!");
    }
}
