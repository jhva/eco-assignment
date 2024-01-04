package com.api.ecoassignment.domain.department.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.ecoassignment.config.restdocs.AbstractRestDocsTests;
import com.api.ecoassignment.domain.department.application.DepartmentService;
import com.api.ecoassignment.domain.department.dto.DepartmentResponseDto;
import com.api.ecoassignment.global.error.BusinessException;
import com.api.ecoassignment.global.error.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest extends AbstractRestDocsTests {

    private static final String EMPLOYEE_URL = "/api/v1/department";


    @MockBean
    private DepartmentService departmentService;

    @Test
    @DisplayName("부서 및 위치 정보를 조회 성공한다")
    void 부서_및_위치_정보를_조회_성공() throws Exception {

        DepartmentResponseDto department = DepartmentResponseDto.builder()
                .departmentId(10L)
                .departmentName("Administration")
                .streetAddress("2004 Charade Rd")
                .postalCode("98199")
                .city("Seattle")
                .stateProvince("Washington")
                .countryName("United States of America")
                .regionName("Americas")
                .build();
        given(departmentService.findDepartmentAndLocationById(10L)).willReturn(department);

        DepartmentResponseDto departResponse = departmentService.findDepartmentAndLocationById(10L);

        mockMvc.perform(get(EMPLOYEE_URL + "/find-department/{id}", department.getDepartmentId())
                        .contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("id").description("부서 id")
                        )));

        Assertions.assertEquals(department.getDepartmentId(), departResponse.getDepartmentId());
        Assertions.assertEquals(department.getDepartmentName(), departResponse.getDepartmentName());
        Assertions.assertEquals(department.getStreetAddress(), departResponse.getStreetAddress());
        Assertions.assertEquals(department.getPostalCode(), departResponse.getPostalCode());
        Assertions.assertEquals(department.getCity(), departResponse.getCity());
        Assertions.assertEquals(department.getStateProvince(), departResponse.getStateProvince());
        Assertions.assertEquals(department.getCountryName(), departResponse.getCountryName());
        Assertions.assertEquals(department.getRegionName(), departResponse.getRegionName());
    }

    @Test
    @DisplayName("부서 및 위치 정보를 조회를 실패한다")
    void 부서_및_위치_정보를_조회_실패() throws Exception {

        given(departmentService.findDepartmentAndLocationById(any())).willThrow(new BusinessException(1000L,
                "departmentId",
                ErrorCode.DEPARTMENT_NOT_FOUND));

        mockMvc.perform(get(EMPLOYEE_URL + "/find-department/{id}", 1000L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}