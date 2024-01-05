package com.api.ecoassignment.domain.department.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.ecoassignment.config.restdocs.AbstractRestDocsTests;
import com.api.ecoassignment.domain.department.application.DepartmentService;
import com.api.ecoassignment.domain.department.dto.request.DepartmentUpdateDto;
import com.api.ecoassignment.domain.department.dto.response.DepartmentResponseDto;
import com.api.ecoassignment.global.error.BusinessException;
import com.api.ecoassignment.global.error.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
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


    private final ObjectMapper objectMapper = new ObjectMapper();


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

    @Test
    @DisplayName("없는 부서를 선택 했을 경우")
    void 부서_업데이트시_조회된_갯수가_0개인_경우_실패() throws Exception {
        DepartmentUpdateDto departmentUpdateDto = DepartmentUpdateDto.builder()
                .departmentName("asdf")
                .percentage(1).build();

        String requestJson = objectMapper.writeValueAsString(departmentUpdateDto);
        given(departmentService.updateDepartmentIncreaseSalary(departmentUpdateDto.getDepartmentName(),
                departmentUpdateDto.getPercentage())).willThrow(
                new BusinessException(departmentUpdateDto.getDepartmentName(),
                        "asdf",
                        ErrorCode.DEPARTMENT_NOT_FOUND));

        mockMvc.perform(patch(EMPLOYEE_URL + "/update-department")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("해당 부서 salary 컬럼이 null 일 떄 ")
    void 컬럼이_null인_경우() throws Exception {
        DepartmentUpdateDto departmentUpdateDto = DepartmentUpdateDto.builder()
                .departmentName("NOC")
                .percentage(10).build();

        String requestJson = objectMapper.writeValueAsString(departmentUpdateDto);
        given(departmentService.updateDepartmentIncreaseSalary(departmentUpdateDto.getDepartmentName(),
                departmentUpdateDto.getPercentage())).willThrow(
                new BusinessException(departmentUpdateDto.getDepartmentName(),
                        "asdf",
                        ErrorCode.DEPARTMENT_SALARY_INFORMATION_MISSING));

        mockMvc.perform(patch(EMPLOYEE_URL + "/update-department")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("퍼센테이지가 100 이상인 경우")
    void 퍼센테이지가_100_이상인_경우() throws Exception {
        DepartmentUpdateDto departmentUpdateDto = DepartmentUpdateDto.builder()
                .departmentName("Purchasing")
                .percentage(100).build();

        String requestJson = objectMapper.writeValueAsString(departmentUpdateDto);
        given(departmentService.updateDepartmentIncreaseSalary(departmentUpdateDto.getDepartmentName(),
                departmentUpdateDto.getPercentage())).willThrow(
                new BusinessException(departmentUpdateDto.getDepartmentName(),
                        "asdf",
                        ErrorCode.PERCENTAGE_MORE_THAN_100));

        mockMvc.perform(patch(EMPLOYEE_URL + "/update-department")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("성공적으로 부서 급여 인상 업데이트")
    void 부서_급여_인상_업데이트_성공() throws Exception {
        DepartmentUpdateDto departmentUpdateDto = DepartmentUpdateDto.builder()
                .departmentName("Purchasing")
                .percentage(10).build();

        String requestJson = objectMapper.writeValueAsString(departmentUpdateDto);
        given(departmentService.updateDepartmentIncreaseSalary(departmentUpdateDto.getDepartmentName(),
                departmentUpdateDto.getPercentage())).willReturn(List.of());

        mockMvc.perform(patch(EMPLOYEE_URL + "/update-department")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data").value("업데이트가 완료 되었습니다."))
                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("departmentName").description("부서 이름"),
                                fieldWithPath("percentage").description("인상 비율")
                        )));
    }
}