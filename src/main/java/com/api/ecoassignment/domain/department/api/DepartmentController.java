package com.api.ecoassignment.domain.department.api;


import com.api.ecoassignment.domain.department.application.DepartmentService;
import com.api.ecoassignment.domain.department.dto.request.DepartmentUpdateDto;
import com.api.ecoassignment.domain.department.dto.response.DepartmentResponseDto;
import com.api.ecoassignment.global.constant.DepartmentEnum;
import com.api.ecoassignment.global.format.apiResponse.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PatchMapping("/update-department")
    public ResponseEntity<ApiResponse> updateDepartment(@RequestBody DepartmentUpdateDto departmentUpdateDto) {
        List<Long> errorDto =
                departmentService.updateDepartmentIncreaseSalary(departmentUpdateDto.getDepartmentName(),
                        departmentUpdateDto.getPercentage());

        String msg = DepartmentEnum.UPDATE_MSG.getMsg();
        if (!errorDto.isEmpty()) {
            msg = String.format(DepartmentEnum.ERROR_MSG.getMsg(), errorDto.size());
        }

        return ResponseEntity.ok()
                .body(ApiResponse.toSuccessForm(msg));
    }
}
