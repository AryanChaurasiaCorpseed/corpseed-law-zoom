package com.master.serviceImpl.stateServiceImpl;

import com.master.dao.countryDao.CountryDao;
import com.master.dao.stateDao.StateDao;
import com.master.dto.stateDto.StateResponse;
import com.master.model.countryModel.Country;
import com.master.model.stateModel.State;
import com.master.response.ResponseEntity;
import com.master.service.stateService.StateService;
import com.master.util.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateDao stateDao;

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private ResponseMapper responseMapper;

    @Override
    public ResponseEntity fetchAllStates(Long countryId) {
        Country country = this.countryDao.findCountryById(countryId);
        if(country!=null)
            return new ResponseEntity().ok(this.stateDao.findStateByCountry(country).stream().map(this::mapToStateResponse));
        else
            return new ResponseEntity().badRequest("Country Not Found !!");
    }

    private StateResponse mapToStateResponse(State state) {
        return this.responseMapper.mapToStateResponse(state);
    }
}
