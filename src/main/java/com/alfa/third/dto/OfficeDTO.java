package com.alfa.third.dto;

public class OfficeDTO {

    private int id;

    private String title;

    private double lon;

    private double lat;

    private String address;

    public OfficeDTO() {
    }

    public OfficeDTO(int id, String title, double lon, double lat, String address) {
        this.id = id;
        this.title = title;
        this.lon = lon;
        this.lat = lat;
        this.address = address;
    }

    public int getId() {
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
