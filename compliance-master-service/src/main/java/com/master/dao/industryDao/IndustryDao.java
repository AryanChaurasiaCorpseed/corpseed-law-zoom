package com.master.dao.industryDao;

import com.master.model.industryModel.Industry;

import java.util.List;

public interface IndustryDao {


    List<Industry> fetchAllIndustry();

    boolean deleteIndustry(Industry industry);

    Industry fetchIndustryById(Long industryId);

    Industry findIndustryByTitle(String title);

    Industry saveIndustry(Industry industry);

    Industry findIndustryByTitleAndIdNot(String title, Long industryId);

    Industry updateIndustry(Industry industry);
}
