package com.api.ecoassignment.domain.department.dao;

import com.api.ecoassignment.domain.department.dto.response.DepartmentAndEmployeeDto;
import com.api.ecoassignment.domain.department.dto.response.DepartmentResponseDto;
import java.util.List;

public interface DepartmentQueryRepository {

    DepartmentResponseDto searchDepartment(Long id);

    List<DepartmentAndEmployeeDto> searchDepartmentByDepartmentName(String name);
}
