package com.api.ecoassignment.domain.employee.dto.response;


import com.api.ecoassignment.domain.employee.entity.Employee;
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

    private String jobId;

    private BigDecimal salary;

    private BigDecimal commissionPct;

    private Long managerId;

    private Long departmentId;

    public static EmployeeResponse entityToDto(Employee employee) {
        EmployeeResponse.EmployeeResponseBuilder builder = EmployeeResponse.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .hireDate(employee.getHireDate())
                .salary(employee.getSalary())
                .commissionPct(employee.getCommissionPct());

        if (employee.getJob() != null) {
            builder.jobId(employee.getJob().getJobId());
        }

        if (employee.getManager() != null) {
            builder.managerId(employee.getManager().getEmployeeId());
        }

        if (employee.getDepartment() != null) {
            builder.departmentId(employee.getDepartment().getDepartmentId());
        }

        return builder.build();
    }

}
