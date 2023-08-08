package com.master.model.subIndustryModel;

import com.master.model.businessActivityModel.BusinessActivity;
import com.master.model.industryModel.Industry;
import lombok.*;
import org.hibernate.annotations.Comment;

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
@Table(name = "sub_industry")
public class SubIndustry {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = Industry.class)
	@JoinColumn(name = "industry_id",nullable = false)
	private Industry industry;
	
	@NotEmpty
	@NotNull
	@NotBlank
	@Column(name = "title")
	private String title;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
	@Comment(value = "1 : Active, 0 : Inactive")
	private boolean isEnable;
	
	@OneToMany(mappedBy = "subIndustry",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<BusinessActivity> businessActivities=new ArrayList<>();



}
