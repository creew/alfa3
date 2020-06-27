package com.alfa.third.services;

import com.alfa.third.dao.OfficeRepository;
import com.alfa.third.dao.entities.Office;
import com.alfa.third.dao.entities.QueueLog;
import com.alfa.third.dto.OfficeDTO;
import com.alfa.third.dto.OfficeDistanceDTO;
import com.alfa.third.dto.OfficePredictDTO;
import com.alfa.third.exceptions.NoOfficeFound;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Calendar.*;

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

    @Transactional
    public OfficePredictDTO getOfficePredict(int office_id, int dayOfWeek, int hourOfDay) {
        Office office = officeRepository.findById(office_id).orElseThrow(() -> new NoOfficeFound("branch not found"));
        List<Double> logs = office.getLogs().stream()
                .filter(data -> {
                    Calendar c = Calendar.getInstance();
                    c.setTime(data.getData());
                    int day = c.get(Calendar.DAY_OF_WEEK);
                    day = day == SUNDAY ? 7 : day - 1;
                    if (dayOfWeek == day) {
                        c.setTime(data.getStartWait());
                        int hour = c.get(Calendar.HOUR_OF_DAY);
                        return hour == hourOfDay;
                    }
                    return false;
                })
                .map(data -> (data.getEndWait().getTime() - data.getStartWait().getTime()) / 1000.0)
                .collect(Collectors.toList());
        double[] vals = new double[logs.size()];
        for (int i = 0; i < logs.size(); i++) {
            vals[i] = logs.get(i);
        }
        double median = new Median().evaluate(vals);
        return new OfficePredictDTO(office.getId(),
                office.getTitle(),
                office.getLon(),
                office.getLat(),
                office.getAddress(),
                dayOfWeek, hourOfDay, (int) Math.round(median));

    }
}
