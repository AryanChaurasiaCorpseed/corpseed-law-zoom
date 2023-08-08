package com.lawzoom.companyservice.dto.companyDto;

import com.lawzoom.companyservice.dto.gstDto.GstResponse;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponse {

    private Long companyId;

    private String companyType;

    private String companyName;

    private String companyState;

    private String companyCity;

    private String companyRegistrationNumber;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date companyRegistrationDate;

    private String companyCINNumber;

    private String companyRemarks;

    private String companyPinCode;

    private String companyAddress;

    private long companyTurnover;

    private String locatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private boolean isEnable;

    private String businessActivity;

    private int permanentEmployee;

    private int contractEmployee;

    private List<GstResponse> gstResponseList;

    private int businessUnits;
}
