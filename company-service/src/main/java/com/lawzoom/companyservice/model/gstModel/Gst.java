package com.lawzoom.companyservice.model.gstModel;

import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
import com.lawzoom.companyservice.model.companyModel.Company;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "gst")
public class Gst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = Company.class,fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id",nullable = false)
	private Company company;
	
	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "gst_number",unique = true)
	private String gstNumber;
	
	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "state_jurisdiction")
	private String stateJurisdiction;
	
	@Column(name = "registration_date")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
	@Comment(value = "1 : Active, 0 : Inactive")
	private boolean isEnable;

	@OneToMany(mappedBy = "gst",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<BusinessUnit> businessUnits=new ArrayList<>();

}
