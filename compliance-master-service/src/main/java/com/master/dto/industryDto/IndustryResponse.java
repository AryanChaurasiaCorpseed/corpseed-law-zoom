package com.master.dto.industryDto;

import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IndustryResponse {

	private Long id;

	private String title;

	private boolean isEnable;

}
