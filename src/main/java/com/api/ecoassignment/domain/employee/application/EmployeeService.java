package com.api.ecoassignment.domain.employee.application;


import com.api.ecoassignment.domain.employee.dao.EmployeeRepository;
import com.api.ecoassignment.domain.employee.dto.response.EmployeeDetailsResponseDto;
import com.api.ecoassignment.domain.employee.dto.response.EmployeeResponse;
import com.api.ecoassignment.domain.employee.entity.Employee;
import com.api.ecoassignment.global.error.BusinessException;
import com.api.ecoassignment.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeResponse findSpecificEmployee(Long employeeId) {
        Employee employeeResponses = findById(employeeId);

        EmployeeResponse employeeResponse = employeeRepository.searchCurrentEmployee(employeeResponses.getEmployeeId());
        return employeeResponse;
    }

    public EmployeeDetailsResponseDto findEmployeeRecord(Long id) {
        Employee employeeResponses = findById(id);
        return employeeRepository.searchSpecificEmployee(employeeResponses.getEmployeeId());
    }

    private Employee findById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new BusinessException(employeeId, "employeeId",
                        ErrorCode.SPECIFIC_EMPLOYEE_NOT_FOUND));
    }
}
