package com.api.ecoassignment.domain.daegu.application;

import com.api.ecoassignment.domain.daegu.dto.response.DaeguResponseDto;
import com.api.ecoassignment.domain.daegu.openapi.pipeline.DaeguPipeLine;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DaeguFoodService {

    private final DaeguPipeLine daeguPipeLine;


    public List<DaeguResponseDto> findDaeguFood() throws ParseException {
        List<DaeguResponseDto> findDaeguFood = daeguPipeLine.fetch();

        return findDaeguFood;
    }
}
