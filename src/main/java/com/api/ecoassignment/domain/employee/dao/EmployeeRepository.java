package com.api.ecoassignment.domain.employee.dao;

import com.api.ecoassignment.domain.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
