package com.api.ecoassignment.domain.department.api;


import com.api.ecoassignment.domain.department.application.DepartmentService;
import com.api.ecoassignment.domain.department.dto.DepartmentResponseDto;
import com.api.ecoassignment.global.format.apiResponse.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/find-department/{id}")
    public ResponseEntity<ApiResponse> findDepartmentAndLocationById(@PathVariable Long id) {
        DepartmentResponseDto departmentResponseDto = departmentService.findDepartmentAndLocationById(id);
        return ResponseEntity.ok()
                .body(ApiResponse.toSuccessForm(departmentResponseDto));
    }
}
