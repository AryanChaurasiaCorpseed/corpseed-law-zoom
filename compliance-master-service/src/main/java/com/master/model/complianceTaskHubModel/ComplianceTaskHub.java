package com.master.model.complianceTaskHubModel;

import com.master.model.complianceHubModel.ComplianceHub;
import com.master.model.complianceSubTaskHubModel.ComplianceSubTaskHub;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
@Table(name = "compliance_task_hub")
public class ComplianceTaskHub {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@NotNull
	@NotEmpty
	@Column(name = "task_name")
	private String taskName;
	
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

	@ManyToOne(targetEntity = ComplianceHub.class,fetch = FetchType.LAZY)
	@JoinColumn(name = "compliance_hub_id",nullable = false)
	private ComplianceHub complianceHub;
	
	@OneToMany(mappedBy = "complianceTaskHub",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ComplianceSubTaskHub> complianceSubTasks=new ArrayList<>();

}
