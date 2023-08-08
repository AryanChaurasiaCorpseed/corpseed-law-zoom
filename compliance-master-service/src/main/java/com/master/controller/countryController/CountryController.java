package com.master.controller.countryController;

import com.master.response.ResponseEntity;
import com.master.service.countryService.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping()
    public ResponseEntity fetchCountryList(){
        return this.countryService.fetchAllCountry();
    }

    @GetMapping("/health")
    public String isOk(){
        return "Health is Ok";
    }
}
