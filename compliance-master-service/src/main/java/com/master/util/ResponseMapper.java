package com.master.util;

import com.master.dto.businessActivityDto.BusinessActivityRequest;
import com.master.dto.businessActivityDto.BusinessActivityResponse;
import com.master.dto.cityDto.CityResponse;
import com.master.dto.compliance.ComplianceMasterResponse;
import com.master.dto.compliancesubtask.ComplianceSubTaskResponse;
import com.master.dto.compliancetask.ComplianceTaskResponse;
import com.master.dto.stateDto.StateResponse;
import com.master.dto.subIndustryDto.SubIndustryRequest;
import com.master.dto.subIndustryDto.SubIndustryResponse;
import com.master.model.businessActivityModel.BusinessActivity;
import com.master.model.cityModel.City;
import com.master.model.complianceHubModel.ComplianceHub;
import com.master.model.complianceSubTaskHubModel.ComplianceSubTaskHub;
import com.master.model.complianceTaskHubModel.ComplianceTaskHub;
import com.master.model.enquiryModel.Enquiry;
import com.master.model.stateModel.State;
import com.master.model.subIndustryModel.SubIndustry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;


@Service
public class ResponseMapper {

    @Autowired
    private RestTemplate restTemplate;


    public SubIndustryResponse mapToSubIndustryResponse(SubIndustry subIndustry) {
        if (subIndustry == null) return null;
        return SubIndustryResponse.builder().id(subIndustry.getId())
                .title(subIndustry.getTitle()).isEnable(subIndustry.isEnable())
                .build();
    }

    public SubIndustry mapToUpdateSubIndustry(SubIndustryRequest subIndustryRequest) {
        if (subIndustryRequest == null) return null;
        return SubIndustry.builder()
                .id(subIndustryRequest.getId()).title(subIndustryRequest.getTitle())
                .industry(subIndustryRequest.getIndustry()).isEnable(subIndustryRequest.isEnable()).build();
    }

    public SubIndustry mapToSaveSubIndustry(SubIndustryRequest subIndustryRequest) {
        if (subIndustryRequest == null) return null;
        return SubIndustry.builder().title(subIndustryRequest.getTitle())
                .createdAt(CommonUtil.getDate()).industry(subIndustryRequest.getIndustry())
                .isEnable(true).build();
    }

    public BusinessActivityResponse mapToBusinessActivityResponse(BusinessActivity ba) {
        if (ba == null) return null;
        return BusinessActivityResponse.builder().id(ba.getId()).title(ba.getTitle())
                .isEnable(ba.isEnable()).build();
    }

    public BusinessActivity mapToSaveBusinessActivity(BusinessActivityRequest baRequest) {
        if (baRequest == null) return null;
        return BusinessActivity.builder().title(baRequest.getTitle())
                .createdAt(CommonUtil.getDate()).isEnable(true)
                .subIndustry(baRequest.getSubIndustry()).build();
    }

    public BusinessActivity mapToUpdateBusinessActivity(BusinessActivityRequest baRequest) {
        if (baRequest == null) return null;
        return BusinessActivity.builder().id(baRequest.getId()).title(baRequest.getTitle())
                .isEnable(baRequest.isEnable()).subIndustry(baRequest.getSubIndustry()).build();
    }

    public StateResponse mapToStateResponse(State state) {
        if (state == null) return null;
        return StateResponse.builder().id(state.getId()).name(state.getName()).build();
    }

    public CityResponse mapToCityResponse(City city) {
        if (city == null) return null;
        return CityResponse.builder().id(city.getId()).name(city.getName()).build();
    }

    public ComplianceMasterResponse mapToComplianceMasterResponse(ComplianceHub c) {

        if (c == null) return null;
        return ComplianceMasterResponse.builder().id(c.getId()).title(c.getTitle())
                .description(c.getDescription()).createdAt(c.getCreatedAt()).updatedAt(c.getUpdatedAt())
                .isEnable(c.isEnable()).duration(c.getDuration()).businessActivity(c.getBusinessActivity())
                .categoryId(c.getComplianceCategoryHub().getId()).priority(c.getPriority())
                .complianceTaskResponseList(c.getComplianceTaskHubList().stream()
                        .map(this::mapToComplianceTaskResponse).collect(Collectors.toList())).build();
    }

    private ComplianceTaskResponse mapToComplianceTaskResponse(ComplianceTaskHub t) {
        if (t == null) return null;
        return ComplianceTaskResponse.builder().id(t.getId()).taskName(t.getTaskName())
                .timelineValue(t.getTimelineValue()).timelineType(t.getTimelineType())
                .createdAt(t.getCreatedAt()).updatedAt(t.getUpdatedAt()).isEnable(t.isEnable())
                .criticality(t.getCriticality()).complianceSubTaskResponseList(
                        t.getComplianceSubTasks().stream().map(this::mapToComplianceSubTask)
                                .collect(Collectors.toList())).build();
    }

    private ComplianceSubTaskResponse mapToComplianceSubTask(ComplianceSubTaskHub s) {
        if (s == null) return null;
        return ComplianceSubTaskResponse.builder().id(s.getId()).subTaskName(s.getSubTaskName())
                .timelineType(s.getTimelineType()).timelineValue(s.getTimelineValue())
                .createdAt(s.getCreatedAt()).updatedAt(s.getUpdatedAt()).isEnable(s.isEnable())
                .criticality(s.getCriticality()).build();
    }
//
//    public Enquiry mapSaveEnquiry(Compliance compliance) {
//        if(compliance==null)return null;
//        return Enquiry.builder().compliance(compliance).message(compliance.getTitle())
//                .postDate(CommonUtil.getDate()).deliveryStatus(2).build();
//    }

}
