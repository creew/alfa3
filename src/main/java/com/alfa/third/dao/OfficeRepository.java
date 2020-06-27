package com.alfa.third.dao;

import com.alfa.third.dao.entities.Office;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Integer> {

}
