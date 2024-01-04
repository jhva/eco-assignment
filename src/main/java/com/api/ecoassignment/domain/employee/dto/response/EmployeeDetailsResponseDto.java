package com.api.ecoassignment.domain.employee.dto.response;


import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class EmployeeDetailsResponseDto {

    private String email;
    private String firstName;
    private BigDecimal commissionPct;
    private String lastName;
    private String phoneNumber;
    private LocalDate hireDate;
    
    private Long employeeId;
    private BigDecimal salary;
    private String jobId;
    private String jobTitle;
    private Integer maxSalary;
    private Integer minSalary;
    private Long departmentId;
    private String departmentName;
    private Long locationId;
    private String city;
    private String postalCode;
    private String stateProvince;
    private String streetAddress;
    private String countryId;
    private String countryName;
    private Long regionId;
    private String regionName;
}