package com.api.ecoassignment.domain.location.dao;

import com.api.ecoassignment.domain.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {

}
