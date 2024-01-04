package com.api.ecoassignment.domain.department.application;


import com.api.ecoassignment.domain.department.dao.DepartmentRepository;
import com.api.ecoassignment.domain.department.dto.DepartmentResponseDto;
import com.api.ecoassignment.domain.department.entity.Department;
import com.api.ecoassignment.global.error.BusinessException;
import com.api.ecoassignment.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;


    public DepartmentResponseDto findDepartmentAndLocationById(Long id) {
        Long departmentId = validateDepartmentCheckId(id);
        return departmentRepository.searchDepartment(departmentId);
    }

    private Long validateDepartmentCheckId(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new BusinessException(id, "departmentId",
                        ErrorCode.DEPARTMENT_NOT_FOUND));
        return department.getDepartmentId();
    }
}
