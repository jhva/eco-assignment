package com.api.ecoassignment.domain.employee.dto.response;


import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class EmployeeResponse {

    private Long employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate hireDate;

    private BigDecimal salary;

    private BigDecimal commissionPct;

    private LocalDate startDate;

    private LocalDate endDate;

}
