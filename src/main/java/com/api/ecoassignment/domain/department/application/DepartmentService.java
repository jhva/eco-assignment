package com.api.ecoassignment.domain.department.application;


import com.api.ecoassignment.domain.department.dao.DepartmentRepository;
import com.api.ecoassignment.domain.department.dto.response.DepartmentAndEmployeeDto;
import com.api.ecoassignment.domain.department.dto.response.DepartmentResponseDto;
import com.api.ecoassignment.domain.department.entity.Department;
import com.api.ecoassignment.global.error.BusinessException;
import com.api.ecoassignment.global.error.ErrorCode;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Transactional
    public List<Long> updateDepartmentIncreaseSalary(String departmentName, Integer impression) {
        List<DepartmentAndEmployeeDto> findDepartmentByDepartmentName = checkDepartmentIsEmptyAndNull(departmentName);
        List<Long> errorEmployeeLongId = new ArrayList<>();
        Map<Boolean, BigDecimal> resultActions = new HashMap<>();

        for (DepartmentAndEmployeeDto departmentAndEmployee : findDepartmentByDepartmentName) {
            processEmployeeData(departmentAndEmployee, impression, resultActions, errorEmployeeLongId);
        }

        return errorEmployeeLongId;
    }

    private void processEmployeeData(DepartmentAndEmployeeDto departmentAndEmployee, Integer impression,
            Map<Boolean, BigDecimal> resultActions, List<Long> errorEmployeeLongId) {

        BigDecimal resultSum = calculateResult(departmentAndEmployee.getSalary(), impression);
        boolean isLessThanMaximumSalary = resultSum.compareTo(departmentAndEmployee.getMaxSalary()) > 0;
        resultActions.put(isLessThanMaximumSalary, resultSum);

        if (!isLessThanMaximumSalary) {
            departmentRepository.updateDepartmentByDepartmentName(resultSum, departmentAndEmployee.getEmployeeId());
        } else {
            errorEmployeeLongId.add(departmentAndEmployee.getEmployeeId());
        }
    }

    private BigDecimal calculateResult(BigDecimal salary, double impression) {
        BigDecimal value1 = new BigDecimal(String.valueOf(salary));
        BigDecimal value2 = new BigDecimal(String.valueOf(impression)).divide(new BigDecimal("100"));
        BigDecimal result = value1.multiply(value2);
        return value1.add(result);
    }


    private List<DepartmentAndEmployeeDto> checkDepartmentIsEmptyAndNull(String departmentName) {
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
