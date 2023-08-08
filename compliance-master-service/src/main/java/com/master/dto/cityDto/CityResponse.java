package com.master.dto.cityDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CityResponse {

    private Long id;

    private String name;
}
