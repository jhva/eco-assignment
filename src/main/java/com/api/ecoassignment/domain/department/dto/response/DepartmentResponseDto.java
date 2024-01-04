package com.api.ecoassignment.domain.department.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DepartmentResponseDto {

    private Long departmentId;

    private String departmentName;

    private String streetAddress;

    private String postalCode;

    private String city;

    private String stateProvince;

    private String countryName;

    private String regionName;
}
