package com.lawzoom.companyservice.utility;

import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitRequest;
import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitResponse;
import com.lawzoom.companyservice.dto.companyDto.CompanyRequest;
import com.lawzoom.companyservice.dto.companyDto.CompanyResponse;
import com.lawzoom.companyservice.dto.gstDto.GstRequest;
import com.lawzoom.companyservice.dto.gstDto.GstResponse;
import com.lawzoom.companyservice.dto.teamDto.TeamRequest;
import com.lawzoom.companyservice.dto.teamDto.TeamResponse;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberRequest;
import com.lawzoom.companyservice.dto.teamMemberDto.TeamMemberResponse;
import com.lawzoom.companyservice.dto.userDto.UserResponse;
import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.gstModel.Gst;
import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
import com.lawzoom.companyservice.model.teamModel.Team;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseMapper {

    public TeamMemberResponse mapToTeamMemberResponse(TeamMember tm) {
        if(tm==null)return null;
        return TeamMemberResponse.builder().id(tm.getId())
                .teamResponse(mapToTeamResponse(tm.getTeam()))
                .userResponse(mapToUserResponse(tm.getMemberId()))
                .isEnable(tm.isEnable()).createdAt(tm.getCreatedAt())
                .build();
    }

    public UserResponse mapToUserResponse(Long userId) {
        return null;
        /*return UserResponse.builder().firstName(user.getFirstName())
                .lastName(user.getLastName()).email(user.getEmail()).mobile(user.getMobile())
                .password(user.getPassword()).designation(user.getDesignation())
                .resourceType(user.getResourceType()).createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt()).isEnable(user.isEnable())
                .build();*/

    }

   public TeamResponse mapToTeamResponse(Team team) {
        if(team==null)return null;
        return TeamResponse.builder().id(team.getId()).teamName(team.getTeamName())
                .isEnable(team.isEnable())
                .createdAt(team.getCreatedAt())
                .build();

    }

    public CompanyResponse mapToCompanyResponse(Company company) {
        if(company==null)return null;
        return CompanyResponse.builder().companyId(company.getId()).companyAddress(company.getAddress())
                .companyName(company.getName()).companyCity(company.getCity())
                .companyState(company.getState()).companyRemarks(company.getRemarks())
                .companyType(company.getCompanyType()).companyTurnover(company.getTurnover())
                .companyId(company.getId()).companyCINNumber(company.getCinNumber())
                .companyPinCode(company.getPinCode()).companyRegistrationDate(company.getRegistrationDate())
                .isEnable(company.isEnable()).companyRegistrationNumber(company.getRegistrationNumber())
                .locatedAt(company.getLocatedAt()).businessActivity(company.getBusinessActivity())
                .permanentEmployee(company.getPermanentEmployee()).contractEmployee(company.getContractEmployee())
                .createdAt(company.getCreatedAt()).updatedAt(company.getUpdatedAt())
                .gstResponseList(mapToCompanyGstResponse(company.getGstList()))
                .businessUnits(countBusinessUnit(company.getGstList()))
                .build();
    }

   private int countBusinessUnit(List<Gst> gstList) {
        if(gstList==null||gstList.isEmpty())return 0;
        return gstList.stream().mapToInt(g->g.getBusinessUnits().size()).sum();
    }

    private List<GstResponse> mapToCompanyGstResponse(List<Gst> gstList) {
        if(gstList==null) return null;
        return gstList.stream().map(this::mapToGstInfoResponse).collect(Collectors.toList());
    }

   public Company mapToSaveCompany(CompanyRequest companyRequest, Long userId) {
        if(companyRequest==null)return null;
        return Company.builder().address(companyRequest.getCompanyAddress())
                .name(companyRequest.getCompanyName()).city(companyRequest.getCompanyCity())
                .state(companyRequest.getCompanyState()).remarks(companyRequest.getCompanyRemarks())
                .companyType(companyRequest.getCompanyType()).turnover(companyRequest.getCompanyTurnover())
                .id(companyRequest.getCompanyId()).cinNumber(companyRequest.getCompanyCINNumber())
                .pinCode(companyRequest.getCompanyPinCode()).registrationDate(companyRequest.getCompanyRegistrationDate())
                .isEnable(true).registrationNumber(companyRequest.getCompanyRegistrationNumber())
                .locatedAt(companyRequest.getLocatedAt()).businessActivity(companyRequest.getBusinessActivity())
                .permanentEmployee(companyRequest.getPermanentEmployee()).contractEmployee(companyRequest.getContractEmployee())
                .createdAt(CommonUtil.getDate()).updatedAt(CommonUtil.getDate()).userId(userId).build();
    }

   public Company mapToUpdateCompany(CompanyRequest companyRequest, Long userId,Company company) {
        if(companyRequest==null)return null;
        return Company.builder().address(companyRequest.getCompanyAddress())
                .name(companyRequest.getCompanyName()).city(companyRequest.getCompanyCity())
                .state(companyRequest.getCompanyState()).remarks(company.getRemarks())
                .companyType(companyRequest.getCompanyType()).turnover(companyRequest.getCompanyTurnover())
                .id(companyRequest.getCompanyId()).cinNumber(company.getCinNumber())
                .pinCode(company.getPinCode()).registrationDate(companyRequest.getCompanyRegistrationDate())
                .isEnable(company.isEnable()).registrationNumber(company.getRegistrationNumber())
                .locatedAt(companyRequest.getLocatedAt()).businessActivity(companyRequest.getBusinessActivity())
                .permanentEmployee(companyRequest.getPermanentEmployee()).contractEmployee(companyRequest.getContractEmployee())
                .createdAt(company.getCreatedAt()).updatedAt(CommonUtil.getDate()).userId(userId).build();
    }

    public Team mapToSaveTeam(TeamRequest t, Company company) {
        if (t == null) return null;
        return Team.builder().company(company).teamName(t.getTeamName())
                .createdAt(CommonUtil.getDate()).updatedAt(CommonUtil.getDate())
                .isEnable(true).build();
    }

//    private User buildNewUser(User user,SuperUser su,Team team) {
//        user.setPassword(CommonUtil.encodePassword(CommonUtil.getPassword(8)));
//        user.setPostDate(CommonUtil.getDate());
//        user.setActiveStatus("Y");
//        user.setSuperUser(su);
//        user.setTeam(team);
//      return user;
//    }

    public Team mapToUpdateTeam(TeamRequest t,Team team) {
        if(t==null)return null;
        return Team.builder().id(team.getId()).company(team.getCompany())
                        .teamName(t.getTeamName()).createdAt(team.getCreatedAt())
                        .updatedAt(CommonUtil.getDate()).isEnable(t.isEnable())
                        .build();
    }

//    private User mapToTeamUpdateUser(User tu, User user) {
//        user.setFirstName(tu.getFirstName());
//        user.setLastName(tu.getLastName());
//        user.setRole(tu.getRole());
//        user.setDesignation(tu.getDesignation());
//        return user;
//    }

   /* public User mapToSaveUser(UserRequest userRequest) {
        if(userRequest==null)return null;
        System.out.println("mapping started.........");
        User user=new User();
        user.setId(0L);
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setMobile(userRequest.getMobile());
        user.setPassword(CommonUtil.encodePassword(userRequest.getPassword()));
        user.setDesignation(userRequest.getDesignation());
        user.setResourceType("In-House");
        user.setCreatedAt(CommonUtil.getDate());
        user.setUpdatedAt(CommonUtil.getDate());
        user.setEnable(true);
        user.setUserRoles(createUserRoles(user));
        System.out.println("After mapping................."+user);
        return user;
    }

    private List<UserRole> createUserRoles(User user) {
        List<UserRole> userRoles=new ArrayList<>();
        userRoles.add(new UserRole(0L,user,"ADMIN"
                ,CommonUtil.getDate(),CommonUtil.getDate(),true));

        return userRoles;
    }*/
   /*

    public User mapToUpdateUser(UserRequest userRequest,SuperUser superUser,User user) {
        if(userRequest==null)return null;
        return User.builder().id(userRequest.getId()).firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName()).email(userRequest.getEmail())
                .mobile(userRequest.getMobile()).password(user.getPassword())
                .designation(userRequest.getDesignation()).postDate(user.getPostDate())
                .activeStatus(userRequest.getActiveStatus()).role(userRequest.getRole())
                .superUser(superUser).build();
    }*/

    public TeamMember mapToUpdateTeamMember(TeamMemberRequest m, Team team) {
        if(m==null)return null;
        return TeamMember.builder().id(m.getId()).team(team).memberId(m.getMemberId())
                .isEnable(m.isEnable()).createdAt(m.getCreatedAt())
                .updatedAt(CommonUtil.getDate()).memberRole(m.getRole())
                .build();
    }

    public TeamMember mapToSaveTeamMember(TeamMemberRequest m, Team team) {
        if(m==null)return null;
        return TeamMember.builder().team(team).memberId(m.getMemberId())
                .memberRole(m.getRole()).createdAt(CommonUtil.getDate())
                .updatedAt(CommonUtil.getDate()).isEnable(true)
                .build();
    }

   public BusinessUnitResponse mapToBusinessUnitResponse(BusinessUnit bu,Gst gst) {
       if(bu==null)return null;
       if(gst==null)
           return BusinessUnitResponse.builder().id(bu.getId()).businessActivity(bu.getBusinessActivity())
                   .city(bu.getCity()).locatedAt(bu.getLocatedAt()).permanentEmployee(bu.getPermanentEmployee())
                   .contractEmployee(bu.getContractEmployee()).address(bu.getAddress())
                   .createdAt(bu.getCreatedAt()).isEnable(bu.isEnable()).build();
       else
           return BusinessUnitResponse.builder().id(bu.getId()).businessActivity(bu.getBusinessActivity())
                   .city(bu.getCity()).locatedAt(bu.getLocatedAt()).permanentEmployee(bu.getPermanentEmployee())
                   .contractEmployee(bu.getContractEmployee()).address(bu.getAddress())
                   .createdAt(bu.getCreatedAt()).isEnable(bu.isEnable())
                   .gstId(gst.getId()).gstNumber(gst.getGstNumber()).gstState(gst.getStateJurisdiction())
                   .build();
   }
    public GstResponse mapToGstResponse(Gst gst,Company company) {
        if(gst==null)return null;
        return GstResponse.builder().id(gst.getId()).stateJurisdiction(gst.getStateJurisdiction())
                .gstNumber(gst.getGstNumber()).registrationDate(gst.getRegistrationDate())
                .createdAt(gst.getCreatedAt()).isEnable(gst.isEnable())
                .companyId(company.getId()).companyName(company.getName()).build();
    }

    public Gst mapToSaveGst(GstRequest gst, Company company) {
        if(gst==null)return null;
        return Gst.builder().gstNumber(gst.getGstNumber()).stateJurisdiction(gst.getStateJurisdiction())
                .registrationDate(gst.getRegistrationDate()).createdAt(CommonUtil.getDate())
                .updatedAt(CommonUtil.getDate()).isEnable(true).company(company).build();
    }

    public Gst mapToUpdateGst(GstRequest gst, Company company) {
        if(gst==null)return null;
        return Gst.builder().id(gst.getId()).gstNumber(gst.getGstNumber())
                .stateJurisdiction(gst.getStateJurisdiction())
                .registrationDate(gst.getRegistrationDate()).createdAt(gst.getCreatedAt())
                .updatedAt(CommonUtil.getDate())
                .isEnable(gst.isEnable()).company(company).build();
    }



    public BusinessUnit mapToSaveBusinessUnit(BusinessUnitRequest bur, Gst gst) {
        if(bur==null)return null;
        return BusinessUnit.builder().businessActivity(bur.getBusinessActivity())
                .city(bur.getCity()).locatedAt(bur.getLocatedAt()).permanentEmployee(bur.getPermanentEmployee())
                .contractEmployee(bur.getContractEmployee()).address(bur.getAddress()).createdAt(CommonUtil.getDate())
                .updatedAt(CommonUtil.getDate()).isEnable(true).gst(gst).build();
    }
/*

    public ResponseEntity companyCompliancesMapRequest(String url, Company company){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return this.restTemplate.postForObject(url,
                new HttpEntity<>(mapToCompanyResponse(company),headers),
                ResponseEntity.class);
    }

    public ResponseEntity businessUnitCompliancesMapRequest(String url,BusinessUnit businessUnit){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return this.restTemplate.postForObject(url,
                new HttpEntity<>(mapToBusinessUnitResponse(businessUnit,null),headers),
                ResponseEntity.class);
    }

    public CompanyResponse mapToCompanyInfoResponse(Company company) {
        if(company==null)return null;
        return CompanyResponse.builder().companyAddress(company.getAddress())
                .companyName(company.getName()).companyCity(company.getCity())
                .companyState(company.getState()).companyRemarks(company.getRemarks())
                .companyType(company.getCompanyType()).companyTurnover(company.getTurnover())
                .companyId(company.getId()).companyCINNumber(company.getCinNumber())
                .companyPinCode(company.getPinCode()).companyRegistrationDate(company.getRegistrationDate())
                .isEnable(company.isEnable()).companyRegistrationNumber(company.getRegistrationNumber())
                .locatedAt(company.getLocatedAt()).businessActivity(company.getBusinessActivity())
                .permanentEmployee(company.getPermanentEmployee()).contractEmployee(company.getContractEmployee())
                .createdAt(company.getCreatedAt())
                .gstResponseList(company.getGstList().stream().map(this::mapToGstInfoResponse).collect(Collectors.toList()))
                .build();
    }*/

    public BusinessUnit mapToUpdateBusinessUnit(BusinessUnitRequest bur, Gst gst) {
        if(bur==null)return null;
        return BusinessUnit.builder().id(bur.getId()).businessActivity(bur.getBusinessActivity())
                .city(bur.getCity()).locatedAt(bur.getLocatedAt()).permanentEmployee(bur.getPermanentEmployee())
                .contractEmployee(bur.getContractEmployee()).address(bur.getAddress()).createdAt(bur.getCreatedAt())
                .updatedAt(CommonUtil.getDate()).isEnable(bur.isEnable()).gst(gst).build();
    }

    private GstResponse mapToGstInfoResponse(Gst gst) {
        if(gst==null)return null;
        return GstResponse.builder().id(gst.getId()).stateJurisdiction(gst.getStateJurisdiction())
                .gstNumber(gst.getGstNumber()).registrationDate(gst.getRegistrationDate())
                .createdAt(gst.getCreatedAt()).isEnable(gst.isEnable())
                .businessUnitResponseList(gst.getBusinessUnits().stream()
                        .map(this::mapToBusinessUnitInfoResponse).collect(Collectors.toList()))
                .build();
    }

    private BusinessUnitResponse mapToBusinessUnitInfoResponse(BusinessUnit bu) {
        if(bu==null)return null;
        return BusinessUnitResponse.builder().id(bu.getId()).businessActivity(bu.getBusinessActivity())
                .city(bu.getCity()).locatedAt(bu.getLocatedAt()).permanentEmployee(bu.getPermanentEmployee())
                .contractEmployee(bu.getContractEmployee()).address(bu.getAddress())
                .createdAt(bu.getCreatedAt()).isEnable(bu.isEnable()).build();
    }
}
