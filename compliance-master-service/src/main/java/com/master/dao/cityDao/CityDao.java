package com.master.dao.cityDao;

import com.master.model.cityModel.City;
import com.master.model.stateModel.State;

import java.util.List;

public interface CityDao {
    List<City> findCityByState(State state);
}
