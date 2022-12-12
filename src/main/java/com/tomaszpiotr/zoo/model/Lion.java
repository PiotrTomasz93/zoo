package com.tomaszpiotr.zoo.model;

import jakarta.persistence.Entity;


@Entity
public class Lion extends Animal implements JumpingAnimal{

    {food = 11;}

    @Override
    public void jump(int height) {
        System.out.println(String.format("Lion jumped to a height of %s meters", height));
    }

    @Override
    public String toString() {
        return "Lion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", food=" + food +
                ", zone=" + zone +
                '}';
    }
}
