package com.tomaszpiotr.zoo.repository;

import com.tomaszpiotr.zoo.model.Animal;
import com.tomaszpiotr.zoo.model.Zone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {

    List<Animal> findAllByZone(Zone zone);
    List<Animal> findAllByName(String animalName);

}