package com.lawzoom.companyservice.dao.gstDao;

import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.gstModel.Gst;

public interface GstDao {
    Gst findGstByCompanyAndGstNumber(Company company, String gstNumber);

    Gst saveGst(Gst gst);

    Gst findGstByCompanyAndGstNumberAndIdNot(Company company, String gstNumber, Long gstId);

    Gst updateGst(Gst mapToUpdateGst);

    Gst findGstByCompanyAndId(Company company, Long gstId);

    boolean deleteGst(Gst gst);

    Gst findGstById(Long gstId);

    Gst findGstByGstNumber(String gstNumber);
}
