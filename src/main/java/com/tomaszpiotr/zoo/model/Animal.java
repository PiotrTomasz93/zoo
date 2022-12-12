package com.tomaszpiotr.zoo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String name;
    public int food;

    @ManyToOne
    @JoinColumn(name = "zoneId")
    @NotNull(message = "Zone cannot be null. Please specify existing zone (field: zoneId)")
    public Zone zone;
}
