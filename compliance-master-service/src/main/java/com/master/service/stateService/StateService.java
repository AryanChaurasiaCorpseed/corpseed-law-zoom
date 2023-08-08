package com.master.service.stateService;

import com.master.response.ResponseEntity;

public interface StateService {
    ResponseEntity fetchAllStates(Long countryId);
}
