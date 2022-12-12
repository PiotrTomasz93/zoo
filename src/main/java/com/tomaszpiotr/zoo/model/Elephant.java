package com.tomaszpiotr.zoo.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
public class Elephant extends Animal{
    {food = 20;}

    @Override
    public String toString() {
        return "Elephant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", food=" + food +
                ", zone=" + zone +
                '}';
    }
}
