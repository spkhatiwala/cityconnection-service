package com.mastercard.cities.cityconnectionservice.controller;


import com.mastercard.cities.cityconnectionservice.service.CityConnectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityConnectionController {


    @Autowired
    CityConnectionServiceImpl cityConnectionServiceImpl;

    @GetMapping("/connected")
    public String getCitiesConnected(@RequestParam String origin, @RequestParam String destination) {
        return cityConnectionServiceImpl.citiesConnected(origin, destination) ? "yes":"no" ;
    }
}
