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
        void 부서_Purchasing에_속하고_급여를_임시로_60퍼센트_인상될때_값이_변하지않는ID는_하나뿐이다() {
            List<Long> data = departmentService.updateDepartmentIncreaseSalary("Purchasing", 10);
            Assertions.assertEquals(data.size(), 0);

        }

        @Test
        void 조회된_갯수가_0개인_경우() {
            assertThatThrownBy(() ->
                    departmentService.updateDepartmentIncreaseSalary("ㅁㄴㅇㄹ", 10))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage("해당 부서를 찾을 수 없습니다. ");
        }

        @Test
        void 컬럼이_null_경우_예외를_발생시킨다() {
            assertThatThrownBy(() ->
                    departmentService.updateDepartmentIncreaseSalary("NOC", 10))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage("해당 부서는 급여 정보가 누락되었습니다. \n 다시 입력해주세요");
        }

        @Test
        void 퍼센테이지가_100이상인_경우() {
            assertThatThrownBy(() ->
                    departmentService.updateDepartmentIncreaseSalary("Purchasing", 100))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage("급여 인상 비율은 100% 이하로 맞춰주세요 ");
        }
    }
}
