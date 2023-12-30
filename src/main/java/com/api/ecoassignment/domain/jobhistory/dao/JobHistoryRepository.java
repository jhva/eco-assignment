package com.api.ecoassignment.domain.jobhistory.dao;

import com.api.ecoassignment.domain.job.entity.Job;
import com.api.ecoassignment.domain.jobhistory.entity.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepository extends JpaRepository<JobHistory,Integer> {

}
