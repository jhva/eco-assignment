package com.api.ecoassignment.domain.department.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.api.ecoassignment.domain.department.dto.response.DepartmentResponseDto;
import com.api.ecoassignment.global.error.BusinessException;
import java.util.List;
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

    @Nested
    class 특정_부서_급여_인상 {

        @Test
        void 특정_부서_급여를_임의의_비율로_계산하고_MAX_SALARY_필드보다_높을_경우_FALSE() {
            List<?> findDepartment = departmentService.updateDepartmentIncreaseSalary("Purchasing");

        }

        @Test
        void 특정_부서_급여를_임의의_비율로_계산하고_MAX_SALARY_필드보다_낮을_경우_TRUE() {

        }

        @Test
        void TRUE_경우_사원ID_기준_급여를_UPDATE_후_저장한다() {

        }

        @Test
        void 조회된_갯수가_0개인_경우() {
            assertThatThrownBy(() ->
                    departmentService.updateDepartmentIncreaseSalary("ㅁㄴㅇㄹ"))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage("해당 부서를 찾을 수 없습니다. ");
        }

        @Test
        void 컬럼이_null_경우_예외를_발생시킨다() {
            assertThatThrownBy(() ->
                    departmentService.updateDepartmentIncreaseSalary("NOC"))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage("해당 부서는 급여 정보가 누락되었습니다. \n 다시 입력해주세요");
        }
    }
}
