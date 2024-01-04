package com.api.ecoassignment.domain.employee.dao;

import com.api.ecoassignment.domain.employee.dto.response.EmployeeDetailsResponseDto;
import com.api.ecoassignment.domain.employee.dto.response.EmployeeResponse;

public interface EmployeeQueryRepository {

    EmployeeDetailsResponseDto searchSpecificEmployee(Long employeeId);

    EmployeeResponse searchCurrentEmployee(Long employeeId);
}
