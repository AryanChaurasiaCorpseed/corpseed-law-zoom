package com.lawzoom.companyservice.dao.companyDao;

import com.lawzoom.companyservice.model.companyModel.Company;

import java.util.List;

public interface CompanyDao {

    Company findCompanyByUserIdAndNameOrRegNoOrCIN(Long userId, String companyName, String regNo, String companyCINNumber);

    Company saveCompany(Company company);

    Company findCompanyByUserIdAndNameOrRegNoOrCINAndCompanyIdNot(Long userId, String companyName, String regNo, String companyCINNumber, Long companyId);

    Company findCompanyById(Long companyId);

    Company updateCompany(Company company);

    List<Company> fetchCompaniesByUserAndNameOrCIN(Long userId, String data);
/*
    boolean deleteCompany(Company company);

    Company findFirstCompanyByUser(User user);*/

    Company findCompanyByUserIdAndCompanyId(Long userId, Long companyId);

    List<Company> findCompanyByUserId(Long userId);
}
