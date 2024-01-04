package com.api.ecoassignment.domain.department.dao;

import com.api.ecoassignment.domain.department.dto.response.DepartmentResponseDto;
import com.api.ecoassignment.global.querydsl.QuerydslConfig;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QuerydslConfig.class)
public class DepartmentRepositoryTest {


    @Autowired
    private DepartmentRepository departmentRepository;


    @Test
    void 부서_및_위치_정보를_조회한다() {
        DepartmentResponseDto departmentResponseDto = departmentRepository.searchDepartment(10L);

        Assertions.assertThat(departmentResponseDto.getDepartmentName()).isEqualTo("Administration");
        Assertions.assertThat(departmentResponseDto.getStreetAddress()).isEqualTo("2004 Charade Rd");
        Assertions.assertThat(departmentResponseDto.getPostalCode()).isEqualTo("98199");
        Assertions.assertThat(departmentResponseDto.getCity()).isEqualTo("Seattle");
        Assertions.assertThat(departmentResponseDto.getStateProvince()).isEqualTo("Washington");
    }

    @Test
    void 특정_부서_급여_인상을_위한_부서와_사원_직업테이블을_조회한다() {
        List<?> departmentAllByDepartmentName = departmentRepository.updateDepartmentIncreaseSalary("Purchasing");

        Assertions.assertThat(departmentAllByDepartmentName.size()).isEqualTo(6);
    }
}
