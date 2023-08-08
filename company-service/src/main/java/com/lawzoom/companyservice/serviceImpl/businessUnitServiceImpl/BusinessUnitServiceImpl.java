package com.lawzoom.companyservice.serviceImpl.businessUnitServiceImpl;

import com.lawzoom.companyservice.dao.businessUnitDao.BusinessUnitDao;
import com.lawzoom.companyservice.dao.gstDao.GstDao;
import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitRequest;
import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitResponse;
import com.lawzoom.companyservice.feignclient.ComplianceMap;
import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
import com.lawzoom.companyservice.model.gstModel.Gst;
import com.lawzoom.companyservice.response.ResponseEntity;
import com.lawzoom.companyservice.service.businessUnitService.BusinessUnitService;
import com.lawzoom.companyservice.utility.ResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

    private BusinessUnitDao businessUnitDao;
    private GstDao gstDao;
    private ResponseMapper responseMapper;
    private ComplianceMap complianceMap;


    @Override
    public ResponseEntity fetchAllBusinessUnitByGstId(Long gstId) {
        Gst gst=this.gstDao.findGstById(gstId);
        if(gst==null)
            return new ResponseEntity().badRequest("Gst Not Found !!");

        return new ResponseEntity().ok(this.businessUnitDao.findBusinessUnitByGst(gst)
                .stream().map(b->mapToBusinessUnitResponse(b,gst)));
    }

    private BusinessUnitResponse mapToBusinessUnitResponse(BusinessUnit businessUnit,Gst gst) {
        return this.responseMapper.mapToBusinessUnitResponse(businessUnit,gst);
    }

   @Override
    public ResponseEntity saveBusinessUnit(BusinessUnitRequest businessUnitRequest, Long gstId) {
        Gst gst=this.gstDao.findGstById(gstId);
        if(gst==null)
            return new ResponseEntity().badRequest("Gst Not Found !!");

        BusinessUnit saveBusinessUnit=this.businessUnitDao.saveBusinessUnit(this.responseMapper.mapToSaveBusinessUnit(businessUnitRequest,gst));
        if(saveBusinessUnit==null)
            return new ResponseEntity().internalServerError();
        //going to fetch compliance and add into company compliance table
       this.complianceMap.mapBusinessCompliances(this.responseMapper.mapToBusinessUnitResponse(saveBusinessUnit,null));
//        this.responseMapper.businessUnitCompliancesMapRequest(complianceService + "api/rest/business-unit/map/compliance/",saveBusinessUnit);

        return new ResponseEntity().ok();
    }

    @Override
    public ResponseEntity updateBusinessUnit(BusinessUnitRequest businessUnitRequest, Long gstId) {
        Gst gst=this.gstDao.findGstById(gstId);
        if(gst==null)
            return new ResponseEntity().badRequest("Gst Not Found !!");

        BusinessUnit updateBusinessUnit=this.businessUnitDao.updateBusinessUnit(
                this.responseMapper.mapToUpdateBusinessUnit(businessUnitRequest,gst));
        if(updateBusinessUnit==null)
            return new ResponseEntity().internalServerError();

        return new ResponseEntity().ok();
    }

    @Override
    public ResponseEntity fetchBusinessUnit(Long businessUnitId, Long gstId) {
        Gst gst=this.gstDao.findGstById(gstId);
        if(gst==null)
            return new ResponseEntity().badRequest("Gst Not Found !!");

        BusinessUnit businessUnit=this.businessUnitDao.findBusinessUnitByGstAndId(gst,businessUnitId);
        if(businessUnit==null)
            return new ResponseEntity().badRequest("Business Unit Not Found !!");

        return new ResponseEntity().ok(mapToBusinessUnitResponse(businessUnit,gst));
    }

    @Override
    public ResponseEntity deleteBusinessUnit(Long businessUnitId, Long gstId) {
        Gst gst=this.gstDao.findGstById(gstId);
        if(gst==null)
            return new ResponseEntity().badRequest("Gst Not Found !!");

        BusinessUnit businessUnit=this.businessUnitDao.findBusinessUnitByGstAndId(gst,businessUnitId);
        if(businessUnit==null)
            return new ResponseEntity().badRequest("Business Unit Not Found !!");

        boolean deleteBusinessUnit=this.businessUnitDao.deleteBusinessUnit(businessUnit);
        if(!deleteBusinessUnit)
            return new ResponseEntity().internalServerError();

        return new ResponseEntity().ok();
    }

   /* @Override
    public BusinessUnitResponse fetchBusinessById(Long businessUnitId) {

        BusinessUnit businessUnit=this.businessUnitDao.findBusinessUnitById(businessUnitId);

        return this.responseMapper.mapToBusinessUnitResponse(businessUnit,null);
    }*/

}
