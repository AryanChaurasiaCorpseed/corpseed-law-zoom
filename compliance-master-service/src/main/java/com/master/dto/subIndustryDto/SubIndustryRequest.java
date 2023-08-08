package com.master.dto.subIndustryDto;

import com.master.model.industryModel.Industry;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubIndustryRequest {

	private Long id;
	
	private String title;

	private boolean isEnable;

	private Industry industry;

}
