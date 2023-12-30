package com.api.ecoassignment.domain.country.dao;

import com.api.ecoassignment.domain.country.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

}
