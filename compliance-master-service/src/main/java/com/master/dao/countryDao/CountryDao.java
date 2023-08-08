package com.master.dao.countryDao;

import com.master.model.countryModel.Country;

import java.util.List;

public interface CountryDao {
    List<Country> findAllCountry();

    Country findCountryById(Long countryId);
}
