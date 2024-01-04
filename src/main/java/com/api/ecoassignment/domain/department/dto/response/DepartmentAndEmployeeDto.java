package com.api.ecoassignment.domain.department.dto.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DepartmentAndEmployeeDto {

    private Long employeeId;
    private String departmentName;
    private BigDecimal salary;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
