package com.api.ecoassignment.domain.department.dao;

import com.api.ecoassignment.domain.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>, DepartmentQueryRepository {

}
