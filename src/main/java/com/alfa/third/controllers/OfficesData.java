package com.alfa.third.controllers;

import com.alfa.third.dao.entities.Office;
import com.alfa.third.dto.OfficeDTO;
import com.alfa.third.dto.OfficeDistanceDTO;
import com.alfa.third.dto.OfficePredictDTO;
import com.alfa.third.exceptions.NoOfficeFound;
import com.alfa.third.services.OfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class OfficesData {
    private final OfficeService officeService;

    public OfficesData(OfficeService officeService) {
        this.officeService = officeService;
    }

    @ExceptionHandler({ NoOfficeFound.class})
    public ResponseEntity<Map<String, String>> exception(NoOfficeFound exception) {
        return new ResponseEntity<>(Collections.singletonMap("status", "branch not found"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/branches/{id}")
    public OfficeDTO getOfficeById(@PathVariable Integer id) {
        return officeService.getOfficeById(id);
    }

    @GetMapping("/branches")
    public OfficeDistanceDTO getOfficeByLonLat(@RequestParam() double lat,
                                               @RequestParam() double lon) {
        return officeService.getOfficeByLonLat(lon, lat);

    }

    @GetMapping("/branches/{id}/predict")
    public OfficePredictDTO getOfficePredict(@RequestParam() int dayOfWeek,
                                             @RequestParam() int hourOfDay,
                                             @PathVariable int id) {
        return officeService.getOfficePredict(id, dayOfWeek, hourOfDay);
    }
}
