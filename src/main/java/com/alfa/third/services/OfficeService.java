package com.alfa.third.services;

import com.alfa.third.dao.OfficeRepository;
import com.alfa.third.dao.entities.Office;
import com.alfa.third.dto.OfficeDTO;
import com.alfa.third.dto.OfficeDistanceDTO;
import com.alfa.third.exceptions.NoOfficeFound;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {

    private final OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public OfficeDTO getOfficeById(int id) {
        Office office = officeRepository.findById(id).orElseThrow(() -> new NoOfficeFound("branch not found"));
        return new OfficeDTO(office.getId(), office.getTitle(),
                office.getLon(), office.getLat(), office.getAddress());
    }

    private double toRad(double degree) {
        return degree * Math.PI / 180;
    }

    private double calcDistance(double lon, double lat, Office office) {
        double lat1 = toRad(lat);
        double lon1 = toRad(lon);
        double lat2 = toRad(office.getLat());
        double lon2 = toRad(office.getLon());
        final double r = 6371000.0;
        double sqrt = Math.pow(Math.sin(lat2 - lat1) / 2, 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow((lon2 - lon1) / 2, 2);
        return 2 * r * Math.asin(Math.sqrt(sqrt));
    }

    public OfficeDistanceDTO getOfficeByLonLat(double lon, double lat) {
        Office min = null;
        double diff = 0;

        Iterable<Office> all = officeRepository.findAll();
        for (Office office : all) {
            double dist = calcDistance(lon, lat, office);
            if (min == null || dist < diff) {
                min = office;
                diff = dist;
            }
        }
        if (min == null)
            throw new NoOfficeFound("branch not found");
        return new OfficeDistanceDTO(min.getId(),
                min.getTitle(),
                min.getLon(),
                min.getLat(),
                min.getAddress(),
                (int) Math.round(diff));
    }
}
