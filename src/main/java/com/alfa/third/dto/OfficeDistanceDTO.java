package com.alfa.third.dto;

public class OfficeDistanceDTO extends OfficeDTO{
    private int distance;

    public int getDistance() {
        return distance;
    }

    public OfficeDistanceDTO(int id, String title, double lon, double lat, String address, int distance) {
        super(id, title, lon, lat, address);
        this.distance = distance;
    }
}
