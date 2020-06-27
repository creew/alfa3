package com.alfa.third.services;

import com.alfa.third.dao.OfficeRepository;
import com.alfa.third.dao.entities.Office;
import com.alfa.third.dto.OfficeDTO;
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
}
