package com.api.ecoassignment.domain.daegu.api;

import com.api.ecoassignment.domain.daegu.application.DaeguFoodService;
import com.api.ecoassignment.domain.daegu.dto.response.DaeguResponseDto;
import com.api.ecoassignment.global.format.apiResponse.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/openapi")
@RequiredArgsConstructor
public class DaeguFoodController {

    private final DaeguFoodService daeguFoodService;


    @GetMapping("/daeguFood")
    public ResponseEntity<ApiResponse> findDaeguFood() throws ParseException {
        List<DaeguResponseDto> daeguResponseDtos = daeguFoodService.findDaeguFood();
        return ResponseEntity.ok()
                .body(ApiResponse.toSuccessForm(daeguResponseDtos));
    }
}
