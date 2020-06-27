package com.alfa.third.dao.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BRANCHES")
public class Office {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private double lon;

    private double lat;

    private String address;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public String getAddress() {
        return address;
    }
}
