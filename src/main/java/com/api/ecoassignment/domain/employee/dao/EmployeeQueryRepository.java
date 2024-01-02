package com.api.ecoassignment.domain.employee.dao;

import com.api.ecoassignment.domain.employee.dto.response.EmployeeDetailsResponseDto;

public interface EmployeeQueryRepository {

    EmployeeDetailsResponseDto findEmployeeDetail(Long employeeId);
}
