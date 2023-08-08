package com.lawzoom.companyservice.model.businessUnitModel;

import com.lawzoom.companyservice.model.gstModel.Gst;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "business_unit")
public class BusinessUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(targetEntity = Gst.class,fetch = FetchType.LAZY)
	@JoinColumn(name = "gst_id",nullable = false)
	private Gst gst;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Column(name = "business_activity")
	private String businessActivity;
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String city;
	
	@Column(name = "located_at")
	private String locatedAt;
	
	@Min(value = 1)
	@Column(name = "permanent_employee")
	private int permanentEmployee;
	
	@Min(value = 0)
	@Column(name = "contract_employee")
	private int contractEmployee;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Column(name = "address",columnDefinition = "TINYTEXT")
	private String address;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
	@Comment(value = "1 : Active, 0 : Inactive")
	private boolean isEnable;
}
