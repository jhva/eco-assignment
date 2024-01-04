package com.api.ecoassignment.domain.employee.application;


import com.api.ecoassignment.domain.employee.dto.response.EmployeeDetailsResponseDto;
import com.api.ecoassignment.domain.employee.dto.response.EmployeeResponse;
import com.api.ecoassignment.global.error.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;


    @Nested
    class 사원_조회 {

        @Test
        void 특정_사원_id가_존재하지_않을_경우_예외가_발생한다() {
            Assertions.assertThrows(BusinessException.class, () -> {
                employeeService.findSpecificEmployee(1L);
            });
        }

        @Test
        void 특정_사원_id_100L_기준으로_조회한다() {

            EmployeeResponse employeeResponse = employeeService.findSpecificEmployee(100L);

            Assertions.assertEquals(employeeResponse.getEmployeeId(), 100L);
        }
    }

    @Nested
    class 사원_이력_조회 {

        @Test
        void 특정_사원_id가_존재하지_않을_경우_예외가_발생한다() {
            Assertions.assertThrows(BusinessException.class, () -> {
                employeeService.findEmployeeRecord(1L);
            });
        }

        @Test
        void 특정_사원_이력을_id_100L_기준으로_조회한다() {

            EmployeeDetailsResponseDto employeeResponse = employeeService.findEmployeeRecord(100L);
            Assertions.assertEquals(employeeResponse.getCity(), "Seattle");
        }
    }
}
