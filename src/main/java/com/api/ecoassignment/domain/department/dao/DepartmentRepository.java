package com.api.ecoassignment.domain.department.dao;

import com.api.ecoassignment.domain.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long>, DepartmentQueryRepository {

}
