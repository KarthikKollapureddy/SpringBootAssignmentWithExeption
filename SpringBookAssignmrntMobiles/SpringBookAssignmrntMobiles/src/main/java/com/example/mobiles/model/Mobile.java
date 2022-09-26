package com.example.mobiles.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


@Entity
public class Mobile {
    @Id
    private int imei;
    private String model;
    private String maker;
    private String color;
    private int price;
    private String processor;
    private byte camera;


}
