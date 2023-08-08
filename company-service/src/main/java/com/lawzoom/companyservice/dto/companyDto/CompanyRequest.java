package com.lawzoom.companyservice.dto.companyDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawzoom.companyservice.model.teamModel.Team;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CompanyRequest {

    private Long companyId;

    private String companyType;

    private String companyName;

    private String companyState;

    private String companyCity;

    private String companyRegistrationNumber;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date companyRegistrationDate;

    private String companyCINNumber;

    private String companyRemarks;

    private String companyPinCode;

    private String companyAddress;

    private long companyTurnover;

    private String locatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date updatedAt;

    private boolean isEnable;

    private String businessActivity;

    private int permanentEmployee;

    private int contractEmployee;

    private Team team;

}
