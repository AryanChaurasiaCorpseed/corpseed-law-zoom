package com.lawzoom.companyservice.serviceImpl.companyServiceImpl;

import com.lawzoom.companyservice.dao.companyDao.CompanyDao;
import com.lawzoom.companyservice.dto.companyDto.CompanyRequest;
import com.lawzoom.companyservice.dto.companyDto.CompanyResponse;
import com.lawzoom.companyservice.feignclient.ComplianceMap;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.response.ResponseEntity;
import com.lawzoom.companyservice.service.companyService.CompanyService;
import com.lawzoom.companyservice.utility.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private ResponseMapper responseMapper;

    @Autowired
    private ComplianceMap complianceMap;


    @Override
    public ResponseEntity fetchAllCompanyByUserId(Long userId) {
        if(userId==0)
            return new ResponseEntity().badRequest("User Not Found !!");

        return new ResponseEntity().ok(this.companyDao.findCompanyByUserId(userId).stream().map(this::mapToCompanyResponse).collect(Collectors.toList()));
    }

    private CompanyResponse mapToCompanyResponse(Company company) {
        return this.responseMapper.mapToCompanyResponse(company);
    }

    @Override
    public ResponseEntity fetchCompanyByUserIdAndCompanyId(Long userId, Long companyId) {
        if(userId==0)
            return new ResponseEntity().badRequest("User Not Found !!");

        Company company=this.companyDao.findCompanyByUserIdAndCompanyId(userId,companyId);

        if(company==null)
            return new ResponseEntity().notFound(Company.class);

        return new ResponseEntity().ok(mapToCompanyResponse(company));
    }

    @Override
    public ResponseEntity saveCompany(Long userId, CompanyRequest companyRequest) {
        if(userId==0)
            return new ResponseEntity().badRequest("User Not Found !!");

        Company findCompany=this.companyDao.findCompanyByUserIdAndNameOrRegNoOrCIN(userId,companyRequest.getCompanyName(),
                companyRequest.getCompanyRegistrationNumber(),companyRequest.getCompanyCINNumber());

        if(findCompany!=null)
            return new ResponseEntity().badRequest("Company already exist !!");

         Company saveCompany=this.companyDao.saveCompany(this.responseMapper.mapToSaveCompany(companyRequest,userId));
         if(saveCompany==null)
             return new ResponseEntity().internalServerError();

         //going to fetch compliance and add into company compliance table
//        System.out.println("Going to call feign client......");
        this.complianceMap.mapCompanyCompliances(this.responseMapper.mapToCompanyResponse(saveCompany));
//        this.responseMapper.companyCompliancesMapRequest(complianceService + "api/rest/company/map/compliance",saveCompany);

        return new ResponseEntity().ok(mapToCompanyResponse(saveCompany));
    }

    @Override
    public ResponseEntity fetchCompaniesByUserAndNameOrCIN(Long userId, String data) {

        List<Company> companyList=this.companyDao.fetchCompaniesByUserAndNameOrCIN(userId,data);

        if(companyList.isEmpty())
            return new ResponseEntity().noContent();

        return new ResponseEntity().ok(companyList.stream().map(this::mapToCompanyResponse));
    }

    @Override
    public ResponseEntity updateCompany(Long userId, CompanyRequest companyRequest) {
        if(userId==0)
            return new ResponseEntity().badRequest("User Not Found !!");

        Company findCompany=this.companyDao.findCompanyByUserIdAndNameOrRegNoOrCINAndCompanyIdNot(userId,companyRequest.getCompanyName(),
                companyRequest.getCompanyRegistrationNumber(),companyRequest.getCompanyCINNumber(),companyRequest.getCompanyId());

        if(findCompany!=null)
            return new ResponseEntity().badRequest("Company already exist !!");

        Company companyById = this.companyDao.findCompanyById(companyRequest.getCompanyId());

        Company updateCompany=this.companyDao.updateCompany(this.responseMapper.mapToUpdateCompany(companyRequest, userId,companyById));
        if(updateCompany==null)
            return new ResponseEntity().internalServerError();

        return new ResponseEntity().ok();
    }
/*
    @Override
    public ResponseEntity deleteCompany(String username, int companyId) {
        User user=this.userDao.findUserByUsername(username);
        if(user==null)
            return new ResponseEntity().badRequest("User Not Found !!");

        Company company=this.companyDao.findCompanyByUserAndCompanyId(user,companyId);
        if(company==null)
            return new ResponseEntity().notFound(Company.class);

        boolean deleteFlag=this.companyDao.deleteCompany(company);

        if(!deleteFlag)
            return new ResponseEntity().internalServerError();

        return new ResponseEntity().ok();
    }

    @Override
    public CompanyResponse fetchCompanyById(int companyId) {
        Company company = this.companyDao.findCompanyById(companyId);

        return mapToCompanyResponse(company);
    }

    @Override
    public ResponseEntity fetchFirstCompanyByUser(String username) {
        User user=this.userDao.findUserByUsername(username);
        if(user==null)
            return new ResponseEntity().badRequest("User Not Found !!");

        return new ResponseEntity().ok(mapToCompanyResponse(this.companyDao.findFirstCompanyByUser(user)));
    }

    @Override
    public ResponseEntity fetchAllCompanyInfoByUser(String username) {
        User user=this.userDao.findUserByUsername(username);
        if(user==null)
            return new ResponseEntity().badRequest("User Not Found !!");

        return new ResponseEntity().ok(this.companyDao.findCompanyByUser(user).stream().map(this::mapToCompanyInfoResponse).collect(Collectors.toList()));
    }*/

    /*private CompanyResponse mapToCompanyInfoResponse(Company company) {
        return this.responseMapper.mapToCompanyInfoResponse(company);
    }*/


}
