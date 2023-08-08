package com.lawzoom.companyservice.model.companyModel;

import com.lawzoom.companyservice.model.gstModel.Gst;
import com.lawzoom.companyservice.model.teamModel.Team;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "company_type")
	@NotNull(message = "Company type can't be null !!")
	@NotBlank(message = "Please select company type !!")
	private String companyType;
	
	@Column(name = "name")
	@NotNull(message = "Company name can't be null !!")
	@NotBlank(message = "Please enter company name !!")
	private String name;
		
	@Column(name = "state")
	@NotNull(message = "State can't be null !!")
	@NotBlank(message = "Please select state !!")
	private String state;
	
	@Column(name = "city")
	@NotNull(message = "City can't be null !!")
	@NotBlank(message = "Please select city !!")
	private String city;
	
	@Column(name = "registration_number")
	private String registrationNumber;
	
	@Column(name = "registration_date")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
	
	@Column(name = "cin_number")
	private String cinNumber;

	@Column(columnDefinition = "TEXT",name="remarks")	
	private String remarks;
	
	@Column(name = "pin_code")
	private String pinCode;
	
	@Column(name="address",columnDefinition = "TINYTEXT")
	private String address;
	
	@Min(value = 0)
	@Column(name = "turnover")
	private long turnover;
	
	@Column(name = "located_at")
	private String locatedAt;
	
	@NotNull(message = "Business activity can't be null !!")
	@NotBlank(message = "Please select business activity !!")
	@Column(name = "business_activity")
	private String businessActivity;
	
	@Min(value = 1)
	@Column(name = "permanent_employee")
	private int permanentEmployee;
	
	@Column(name = "contract_employee")
	private int contractEmployee;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
	@Comment(value = "1 : Active, 0 : Inactive")
	private boolean isEnable;

	@OneToMany(mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Team> teams;

	@OneToMany(mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Gst> gstList;

	@Override
	public String toString() {
		return "Company{" +
				"id=" + id +
				", userId=" + userId +
				", companyType='" + companyType + '\'' +
				", name='" + name + '\'' +
				", state='" + state + '\'' +
				", city='" + city + '\'' +
				", registrationNumber='" + registrationNumber + '\'' +
				", registrationDate=" + registrationDate +
				", cinNumber='" + cinNumber + '\'' +
				", remarks='" + remarks + '\'' +
				", pinCode='" + pinCode + '\'' +
				", address='" + address + '\'' +
				", turnover=" + turnover +
				", locatedAt='" + locatedAt + '\'' +
				", businessActivity='" + businessActivity + '\'' +
				", permanentEmployee=" + permanentEmployee +
				", contractEmployee=" + contractEmployee +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				", isEnable=" + isEnable +
				'}';
	}
}
