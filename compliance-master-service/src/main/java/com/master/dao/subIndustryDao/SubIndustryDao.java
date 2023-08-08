package com.master.dao.subIndustryDao;


import com.master.model.industryModel.Industry;
import com.master.model.subIndustryModel.SubIndustry;

import java.util.List;

public interface SubIndustryDao {

    SubIndustry fetchSubIndustryById(Long subIndustryId);

    boolean deleteSubIndustry(SubIndustry subIndustry);

    List<SubIndustry> fetchAllSubIndustry();

    SubIndustry findSubIndustryByIndustryAndTitle(Industry industry, String title);

    SubIndustry saveSubIndustry(SubIndustry y);

    SubIndustry findSubIndustryByIndustryAndTitleAndIdNot(Industry industry,String title, Long subIndustryId);

    SubIndustry updateSubIndustry(SubIndustry build);

    List<SubIndustry> fetchSubIndustryByIndustry(Industry industry);
}
