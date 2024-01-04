package com.api.ecoassignment.domain.department.dao;

import com.api.ecoassignment.domain.department.dto.DepartmentResponseDto;

public interface DepartmentQueryRepository {

    DepartmentResponseDto searchDepartment(Long name);
}
