package com.api.ecoassignment.domain.employee.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.ecoassignment.config.restdocs.AbstractRestDocsTests;
import com.api.ecoassignment.domain.employee.application.EmployeeService;
import com.api.ecoassignment.domain.employee.dto.response.EmployeeResponse;
import com.api.ecoassignment.global.error.BusinessException;
import com.api.ecoassignment.global.error.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest extends AbstractRestDocsTests {

    private static final String EMPLOYEE_URL = "/api/v1/employee";

    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private EmployeeService employeeService;


    @Test
    @DisplayName("성공적으로 특정 사원의 정보를 조회한다")
    void 특정_사원의_현재_정보를_조회한다() throws Exception {

        EmployeeResponse employee = EmployeeResponse.builder()
                .employeeId(100L)
                .firstName("Steven")
                .lastName("King")
                .email("SKING")
                .phoneNumber("515.123.4567")
                .managerId(null)
                .jobId("AD_PRES")
                .salary(BigDecimal.valueOf((float) 24000.00))
                .departmentId(90L)
                .hireDate(LocalDate.of(1987, 06, 17)).build();
        given(employeeService.findSpecificEmployee(100L)).willReturn(employee);

        EmployeeResponse employeeResponse = employeeService.findSpecificEmployee(100L);

        mockMvc.perform(get(EMPLOYEE_URL + "/find-specific/{id}", employeeResponse.getEmployeeId())
                        .contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("id").description("특정 사원 id")
                        )));

        Assertions.assertEquals(employee.getEmployeeId(), employeeResponse.getEmployeeId());
        Assertions.assertEquals(employee.getFirstName(), employeeResponse.getFirstName());
        Assertions.assertEquals(employee.getLastName(), employeeResponse.getLastName());
        Assertions.assertEquals(employee.getEmail(), employeeResponse.getEmail());

    }

    @Test
    @DisplayName("특정 사원의 정보를 조회하는데 실패한다")
    void 특정_사원의_현재_정보_실패() throws Exception {

        given(employeeService.findSpecificEmployee(any())).willThrow(new BusinessException(1L, "employeeId",
                ErrorCode.SPECIFIC_EMPLOYEE_NOT_FOUND));

        mockMvc.perform(get(EMPLOYEE_URL + "/find-specific/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
