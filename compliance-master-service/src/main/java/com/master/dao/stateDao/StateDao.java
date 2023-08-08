package com.master.dao.stateDao;

import com.master.model.countryModel.Country;
import com.master.model.stateModel.State;

import java.util.List;

public interface StateDao {
    List<State> findStateByCountry(Country country);

    State findStateById(Long stateId);
}
