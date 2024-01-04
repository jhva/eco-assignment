package com.api.ecoassignment.domain.employee.dao;


import com.api.ecoassignment.domain.employee.dto.response.EmployeeDetailsResponseDto;
import com.api.ecoassignment.domain.employee.dto.response.EmployeeResponse;
import com.api.ecoassignment.global.querydsl.QuerydslConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QuerydslConfig.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    public void 특정_사원의_현재_정보를_사원ID_기준으로_조회한다() {
        EmployeeResponse employee = employeeRepository.searchCurrentEmployee(100L);

        Assertions.assertEquals(employee.getFirstName(), "Steven");
        Assertions.assertEquals(employee.getEmployeeId(), 100);
    }

    @Test
    public void 특정_사원의_이력_정보를_사원ID_기준으로_조회한다() {
        EmployeeDetailsResponseDto employeeDetailsResponseDto = employeeRepository.searchSpecificEmployee(101L);

        Assertions.assertNotNull(employeeDetailsResponseDto);
    }
}
