package com.lawzoom.companyservice.dao.businessUnitDao;

import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
import com.lawzoom.companyservice.model.gstModel.Gst;
import org.springframework.beans.PropertyValues;

import java.util.List;

public interface BusinessUnitDao {

    BusinessUnit saveBusinessUnit(BusinessUnit businessUnit);

    BusinessUnit updateBusinessUnit(BusinessUnit businessUnit);

    BusinessUnit findBusinessUnitByGstAndId(Gst gst, Long businessUnitId);

    boolean deleteBusinessUnit(BusinessUnit businessUnit);

    BusinessUnit findBusinessUnitById(Long businessUnitId);

    List<BusinessUnit> findBusinessUnitByGst(Gst gst);
}
