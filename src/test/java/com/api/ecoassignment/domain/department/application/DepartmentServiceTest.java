package com.api.ecoassignment.domain.department.application;

import com.api.ecoassignment.domain.department.dto.response.DepartmentResponseDto;
import com.api.ecoassignment.global.error.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Nested
    class 부서_및_위치정보_조회 {

        @Test
        void 부서_id가_존재하지_않을_경우_예외가_발생한다() {
            Assertions.assertThrows(BusinessException.class, () -> {
                departmentService.findDepartmentAndLocationById(1000L);
            });
        }

        @Test
        void 부서_id_10L_기준으로_조회한다() {

            DepartmentResponseDto employeeResponse = departmentService.findDepartmentAndLocationById(10L);

            Assertions.assertEquals(employeeResponse.getDepartmentId(), 10L);
        }
    }

}
