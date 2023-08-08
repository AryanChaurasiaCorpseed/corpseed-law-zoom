package com.master.model.complianceHubModel;

import com.master.model.complianceCategoryHub.ComplianceCategoryHub;
import com.master.model.complianceTaskHubModel.ComplianceTaskHub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Entity
@Table(name = "compliance_hub")
public class ComplianceHub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String title;

    @NotNull
    @NotBlank
    @NotEmpty
    private String description;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
    @Comment(value = "1 : Active, 0 : Inactive")
    private boolean isEnable;

    private String duration;

    @Column(name = "business_activity")
    private String businessActivity;

    @Comment(value="1 : Mandatory Compliance, 2: Optional Compliance")
    private int priority;

    @ManyToOne(targetEntity = ComplianceCategoryHub.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "compliance_category_hub_id",nullable = false)
    private ComplianceCategoryHub complianceCategoryHub;

    @OneToMany(mappedBy = "complianceHub",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ComplianceTaskHub> complianceTaskHubList=new ArrayList<>();

}
