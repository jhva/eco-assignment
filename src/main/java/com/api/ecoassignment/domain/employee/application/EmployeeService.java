package com.api.ecoassignment.domain.employee.application;


import com.api.ecoassignment.domain.employee.dao.EmployeeRepository;
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
        Employee employeeResponses =
                employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new BusinessException(employeeId, "employeeId",
                                ErrorCode.SPECIFIC_EMPLOYEE_NOT_FOUND));

        EmployeeResponse employeeResponse = EmployeeResponse.entityToDto(employeeResponses);
        return employeeResponse;
    }
}
