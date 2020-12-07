package com.mastercard.cities.cityconnectionservice.service;

import com.mastercard.cities.cityconnectionservice.util.CityConnectionUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityConnectionServiceImpl implements CityConnectionService {

    @Autowired
    CityConnectionUtilities cityConnectionUtilities;

    @Override
    public boolean citiesConnected(String origin, String destination){
        return cityConnectionUtilities.citiesConnected(origin,destination);
    }
}
