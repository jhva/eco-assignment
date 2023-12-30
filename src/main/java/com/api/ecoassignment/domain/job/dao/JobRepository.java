package com.api.ecoassignment.domain.job.dao;

import com.api.ecoassignment.domain.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,String> {

}
