package com.api.ecoassignment.domain.daegu.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.api.ecoassignment.domain.daegu.dto.response.DaeguResponseDto;
import com.api.ecoassignment.domain.daegu.openapi.pipeline.DaeguPipeLine;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DaeguPipeLineTest {

    @Autowired
    private DaeguPipeLine daeguPipeLine;

    @Test
    void 대구광역시_음식_골목_조회_테스트() throws UnsupportedEncodingException, ParseException {
        List<DaeguResponseDto> responseEntity = daeguPipeLine.fetch();

        assertNotNull(responseEntity);
    }
}
