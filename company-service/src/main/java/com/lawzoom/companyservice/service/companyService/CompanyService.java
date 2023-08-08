package com.lawzoom.companyservice.service.companyService;

import com.lawzoom.companyservice.dto.companyDto.CompanyRequest;
import com.lawzoom.companyservice.response.ResponseEntity;

public interface CompanyService {
    ResponseEntity fetchAllCompanyByUserId(Long userId);

    ResponseEntity fetchCompanyByUserIdAndCompanyId(Long userId, Long companyId);

    ResponseEntity saveCompany(Long userId, CompanyRequest companyRequest);

    ResponseEntity updateCompany(Long userId,CompanyRequest companyRequest);

    ResponseEntity fetchCompaniesByUserAndNameOrCIN(Long userId,String data);
    /*

    ResponseEntity deleteCompany(String username, int companyId);
    CompanyResponse fetchCompanyById(int companyId);
    ResponseEntity fetchFirstCompanyByUser(String username);
    ResponseEntity fetchAllCompanyInfoByUser(String username);*/
}
