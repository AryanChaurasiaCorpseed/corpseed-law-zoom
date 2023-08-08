package com.master.dto.businessActivityDto;

import com.master.model.subIndustryModel.SubIndustry;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusinessActivityRequest {

	private Long id;
	
	private String title;

	private boolean isEnable;

	private SubIndustry subIndustry;

}
