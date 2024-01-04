package com.api.ecoassignment.domain.department.application;


import com.api.ecoassignment.domain.department.dao.DepartmentRepository;
import com.api.ecoassignment.domain.department.dto.response.DepartmentAndEmployeeDto;
import com.api.ecoassignment.domain.department.dto.response.DepartmentResponseDto;
import com.api.ecoassignment.domain.department.entity.Department;
import com.api.ecoassignment.global.error.BusinessException;
import com.api.ecoassignment.global.error.ErrorCode;
import java.util.List;
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

    public List<?> updateDepartmentIncreaseSalary(String departmentName) {

        List<?> findDepartmentByDepartmentName = checkDepartmentIsEmptyAndNull(departmentName);

        return findDepartmentByDepartmentName;
    }


    public List<DepartmentAndEmployeeDto> checkDepartmentIsEmptyAndNull(String departmentName) {
        List<DepartmentAndEmployeeDto> department = departmentRepository.searchDepartmentByDepartmentName(
                departmentName);

        if (department.isEmpty()) {
            throw new BusinessException(department, "departmentName", ErrorCode.DEPARTMENT_NOT_FOUND);
        }
        if (department.get(0).getEmployeeId() == null) {
            throw new BusinessException(departmentName, "departmentName",
                    ErrorCode.DEPARTMENT_SALARY_INFORMATION_MISSING);
        }
        return department;
    }
}
