package com.alfa.third.controllers;

import com.alfa.third.dto.OfficeDTO;
import com.alfa.third.exceptions.NoOfficeFound;
import com.alfa.third.services.OfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
