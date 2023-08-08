package com.master.model.complianceSubTaskHubModel;

import com.master.model.complianceTaskHubModel.ComplianceTaskHub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

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
@Entity
@Table(name = "compliance_sub_task_hub")
public class ComplianceSubTaskHub {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@NotNull
	@NotEmpty
	@Column(name = "sub_task_name")
	private String subTaskName;
	
	@Min(value = 1)
	@Column(name = "timeline_value")
	private int timelineValue;
	
	@NotBlank
	@NotNull
	@NotEmpty
	@Column(name = "timeline_type")
	private String timelineType;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
	@Comment(value = "1 : Active, 0 : Inactive")
	private boolean isEnable;

	private String criticality;

	@ManyToOne(targetEntity = ComplianceTaskHub.class,fetch = FetchType.LAZY)
	@JoinColumn(name = "compliance_task_hub_id",nullable = false)
	private ComplianceTaskHub complianceTaskHub;

}
