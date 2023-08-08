package com.master.dto.subIndustryDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubIndustryResponse {

	private Long id;
	
	private String title;

	private boolean isEnable;

}
