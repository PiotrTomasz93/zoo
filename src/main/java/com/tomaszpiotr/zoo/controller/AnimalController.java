package com.tomaszpiotr.zoo.controller;

import com.tomaszpiotr.zoo.model.*;
import com.tomaszpiotr.zoo.service.AnimalService;
import com.tomaszpiotr.zoo.service.ZoneService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "animal")
@Validated
public class AnimalController {


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Validation error: Error details: " + System.lineSeparator() + e.getMessage(),  HttpStatus.BAD_REQUEST);
    }


    @Autowired
    private AnimalService animalService;


    @GetMapping(value = "/")
    public String index(){
        return "Welcome in Zoo application";
    }

    @PostMapping(value = "/elephant")
    public ResponseEntity<String> addElephant(@RequestBody Elephant elephant){
        System.out.println("serivce elephant: " + elephant);
        animalService.save(elephant);
        return new ResponseEntity<>("Animal added." + System.lineSeparator() + elephant.toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/lion")
    public ResponseEntity<String> addLion(@RequestBody Lion lion){
        System.out.println("serivce elephant: " + lion);
        animalService.save(lion);
        return new ResponseEntity<>("Animal added." + System.lineSeparator() + lion.toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/rabbit")
    public ResponseEntity<String> addLion(@RequestBody Rabbit rabbit){
        System.out.println("serivce elephant: " + rabbit);
        animalService.save(rabbit);
        return new ResponseEntity<>("Animal added." + System.lineSeparator() + rabbit.toString(), HttpStatus.OK);
    }

    @GetMapping(value ="/animals")
    public List<Animal> getAnimalsByZoneId(@RequestParam long zoneId){
        return animalService.getAnimals(zoneId);
    }

    @GetMapping(value ="/animals/{animalName}")
    public List<Animal> getAnimalsByName(@PathVariable String animalName){
        return animalService.getAnimalsByName(animalName);
    }

}
