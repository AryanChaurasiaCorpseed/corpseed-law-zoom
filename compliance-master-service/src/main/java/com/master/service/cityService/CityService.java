package com.master.service.cityService;

import com.master.response.ResponseEntity;

public interface CityService {
    ResponseEntity fetchAllCity(Long stateId);
}
