package com.api.ecoassignment.domain.department.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentUpdateDto {

    private int percentage;
    private String departmentName;
}
