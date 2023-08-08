package com.master.serviceImpl.countryServiceImpl;

import com.master.dao.countryDao.CountryDao;
import com.master.response.ResponseEntity;
import com.master.service.countryService.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    @Override
    public ResponseEntity fetchAllCountry() {
        return new ResponseEntity().ok(this.countryDao.findAllCountry());
    }
}
