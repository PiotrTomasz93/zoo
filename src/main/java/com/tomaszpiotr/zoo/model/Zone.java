package com.tomaszpiotr.zoo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zone {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long zoneId;
    private String name;




    @Override
    public String toString() {
        return "Zone{" +
                "zoneId=" + zoneId +
                ", name='" + name + '\'' +
                '}';
    }
}
