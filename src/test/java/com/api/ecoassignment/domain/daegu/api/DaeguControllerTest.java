package com.api.ecoassignment.domain.daegu.api;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.ecoassignment.config.restdocs.AbstractRestDocsTests;
import com.api.ecoassignment.domain.daegu.application.DaeguFoodService;
import com.api.ecoassignment.domain.daegu.dto.response.DaeguResponseDto;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(DaeguFoodController.class)
public class DaeguControllerTest extends AbstractRestDocsTests {

    private static final String DAEGU_URL = "/api/v1/openapi/daeguFood";

    @MockBean
    private DaeguFoodService daeguFoodService;

    @Test
    void 대구_음식_골목점_조회_성공() throws Exception {
        List<DaeguResponseDto> daeguResponseDtos = new ArrayList<>();
        DaeguResponseDto daeguResponseDto = DaeguResponseDto.builder()
                .cnt("1")
                .OPENDATA_ID("TL-11")
                .FD_CS("(중구)남산동보쌈골목")
                .SMPL_DESC("이른 새벽부터 허기진 배 달래주는 돼지국밥과 보쌈~").build();
        daeguResponseDtos.add(daeguResponseDto);
        given(daeguFoodService.findDaeguFood()).willReturn(daeguResponseDtos);

        List<DaeguResponseDto> originalDto = daeguFoodService.findDaeguFood();
        mockMvc.perform(
                        get(DAEGU_URL))
                .andExpect(status().isOk());

        Assertions.assertThat(daeguResponseDtos.get(0).getCnt()).isEqualTo(originalDto.get(0).getCnt());
    }
}
