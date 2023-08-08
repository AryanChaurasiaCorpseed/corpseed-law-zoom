package com.master.dao.businessActivityDao;

import com.master.model.businessActivityModel.BusinessActivity;
import com.master.model.subIndustryModel.SubIndustry;

import java.util.List;

public interface BusinessActivityDao {

    List<BusinessActivity> fetchAllBusinessActivity();

    BusinessActivity findBusinessActivityBySubIndustryAndTitle(SubIndustry subIndustry, String title);

    BusinessActivity saveBusinessActivity(BusinessActivity businessActivity);

    BusinessActivity findBusinessActivityBySubIndustryAndTitleAndIdNot(SubIndustry subIndustry, String title, Long businessActivityId);

    BusinessActivity updateBusinessActivity(BusinessActivity businessActivity);

    BusinessActivity fetchBusinessActivityById(Long businessActivityId);

    boolean deleteBusinessActivity(BusinessActivity businessActivity);

    List<BusinessActivity> findBusinessActivityBySubIndustry(SubIndustry subIndustry);

    List<BusinessActivity> findBusinessActivityContains(String searchData);
}
