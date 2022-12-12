package com.tomaszpiotr.zoo.service;

import com.tomaszpiotr.zoo.model.Animal;
import com.tomaszpiotr.zoo.model.Zone;
import com.tomaszpiotr.zoo.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {


    @Autowired
    public AnimalRepository animalRepository;

    public void save(Animal animal){
        animalRepository.save(animal);
    }

    public List<Animal> getAnimals(long zoneId) {
        Zone zone = new Zone();
        zone.setZoneId(zoneId);
        return animalRepository.findAllByZone(zone);

    }

    public List<Animal> getAnimalsByName(String animalName) {
        return animalRepository.findAllByName(animalName);
    }

}
