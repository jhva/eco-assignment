package com.api.ecoassignment.domain.employee.api;

import com.api.ecoassignment.domain.employee.application.EmployeeService;
import com.api.ecoassignment.domain.employee.dto.response.EmployeeDetailsResponseDto;
import com.api.ecoassignment.domain.employee.dto.response.EmployeeResponse;
import com.api.ecoassignment.global.format.apiResponse.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/find-specific/{id}")
    public ResponseEntity<ApiResponse> findSpecificEmployee(@PathVariable Long id) {
        EmployeeResponse employeeResponse = employeeService.findSpecificEmployee(id);
        return ResponseEntity.ok()
                .body(ApiResponse.toSuccessForm(employeeResponse));
    }

    @GetMapping("/detail-find/{id}")
    public ResponseEntity<ApiResponse> findDetailSpecific(@PathVariable Long id) {
        EmployeeDetailsResponseDto employeeDetailsResponseDto = employeeService.findEmployeeRecord(id);
        return ResponseEntity.ok().body(ApiResponse.toSuccessForm(employeeDetailsResponseDto));
    }
}
