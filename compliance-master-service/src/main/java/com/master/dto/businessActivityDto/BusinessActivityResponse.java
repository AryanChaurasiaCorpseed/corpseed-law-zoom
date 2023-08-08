package com.master.dto.businessActivityDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusinessActivityResponse {

	private Long id;
	
	private String title;

	private boolean isEnable;
}
