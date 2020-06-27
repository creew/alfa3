package com.alfa.third.dto;

public class OfficePredictDTO extends OfficeDTO {
    private int dayOfWeek;

    private int hourOfDay;

    private int predicting;

    public OfficePredictDTO(int id, String title, double lon, double lat, String address, int dayOfWeek, int hourOfDay, int predicting) {
        super(id, title, lon, lat, address);
        this.dayOfWeek = dayOfWeek;
        this.hourOfDay = hourOfDay;
        this.predicting = predicting;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public int getPredicting() {
        return predicting;
    }
}
