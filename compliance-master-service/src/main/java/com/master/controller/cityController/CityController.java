package com.master.controller.cityController;

import com.master.response.ResponseEntity;
import com.master.service.cityService.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/master/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/{stateId}")
    public ResponseEntity fetchCityList(@PathVariable("stateId") Long stateId){
        return this.cityService.fetchAllCity(stateId);
    }
}
