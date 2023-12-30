package com.api.ecoassignment.domain.employee.dao;


import com.api.ecoassignment.domain.employee.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void 특정_사원의_현재_정보를_사원ID_기준으로_조회한다() {
        Employee employee = employeeRepository.findById(100L).orElse(null);

        Assertions.assertEquals(employee.getFirstName(), "Steven");
        Assertions.assertEquals(employee.getEmployeeId(), 100);
    }
}
