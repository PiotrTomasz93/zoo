package com.tomaszpiotr.zoo.controller;

import com.tomaszpiotr.zoo.model.*;
import com.tomaszpiotr.zoo.service.ZoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "zone")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping("/zone")
    public ResponseEntity<String> addZone(@RequestBody Zone zone){
        System.out.println("service zone: " + zone);
        zoneService.save(zone);
        return new ResponseEntity<>("Zone added." + System.lineSeparator() + zone.toString(), HttpStatus.OK);
    }

    @GetMapping("/zoneWithMaxFoodNeeded")
    public String getZoneWithMaxFoodNeeded(){
        return zoneService.getZonesWithMaxFoodNeeded();
    }

    @GetMapping("/zoneWithMinAnimals")
    public String getZoneWithMinAnimals(){
        return zoneService.getZonesWithMinNumberOfAnimals();
    }



}
