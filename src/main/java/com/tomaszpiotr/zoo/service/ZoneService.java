package com.tomaszpiotr.zoo.service;

import com.tomaszpiotr.zoo.model.Animal;
import com.tomaszpiotr.zoo.model.Zone;
import com.tomaszpiotr.zoo.repository.AnimalRepository;
import com.tomaszpiotr.zoo.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public void save(Zone zone){
        zoneRepository.save(zone);
    }

    public Zone getZone(long zone_id){
        Optional<Zone> zoneOptional = zoneRepository.findById(zone_id);
        if (zoneOptional.isPresent()){
           return  zoneOptional.get();
        }
        return null;
    }

    public String getZonesWithMaxFoodNeeded() {
        List<Animal> animals = (List) animalRepository.findAll();
        if(animals.isEmpty()){
            return "There are no animals in database";
        }
        Map<Zone, Integer> zoneFoodMap = animals.stream()
                .collect(Collectors.groupingBy(Animal::getZone, Collectors.summingInt(Animal::getFood)));

        List<Zone> zoneList = zoneFoodMap.entrySet().stream()
                .filter(e -> e.getValue() == getMaxFood(zoneFoodMap))
                .map(e -> e.getKey())
                .toList();

        return String.format("Zones with max amount of food needed (%d units): %s", getMaxFood(zoneFoodMap), zoneList.toString()) ;
    }

    public int getMaxFood (Map<Zone, Integer> zoneFoodMap){
        if (!zoneFoodMap.isEmpty()){
            return zoneFoodMap.values().stream()
                    .sorted(Comparator.reverseOrder())
                    .toList().get(0);
        }
        return -1;
    }



    public String getZonesWithMinNumberOfAnimals() {
        List<Animal> animals = (List) animalRepository.findAll();
        if(animals.isEmpty()){
            return "There are no animals in database";
        }
        Map<Zone, Long> zoneCountMap = animals.stream()
                .collect(Collectors.groupingBy(Animal::getZone, Collectors.counting()));


        List<Zone> zonesFiltered = zoneCountMap.entrySet().stream()
                .filter(x -> x.getValue() == getMinNumber(zoneCountMap))
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());


        return String.format("Zones with the fewest animals: %s", zonesFiltered.toString()) ;
    }

    public long getMinNumber(Map<Zone, Long> zoneCountMap){
        if (!zoneCountMap.isEmpty()){
            Optional<Long> min = zoneCountMap.values().stream().min(Comparator.comparingLong(Long::longValue));
            if (min.isPresent()){
                return min.get();
            }
        }
        return -1;
    }


}
