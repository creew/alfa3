package com.alfa.third.dao.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<QueueLog> logs  = new HashSet<>();

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

    public Set<QueueLog> getLogs() {
        return logs;
    }
}
