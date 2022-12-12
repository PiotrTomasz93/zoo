package com.tomaszpiotr.zoo.model;

import jakarta.persistence.Entity;


@Entity
public class Rabbit extends Animal implements JumpingAnimal{
    {food = 4;}

    @Override
    public void jump(int height) {
        System.out.println(String.format("Rabbit jumped to a height of %s meters", height));
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", food=" + food +
                ", zone=" + zone +
                '}';
    }
}
