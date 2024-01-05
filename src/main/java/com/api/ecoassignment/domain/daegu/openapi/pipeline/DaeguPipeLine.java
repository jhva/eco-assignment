package com.api.ecoassignment.domain.daegu.openapi.pipeline;

import com.api.ecoassignment.domain.daegu.dto.response.DaeguResponseDto;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class DaeguPipeLine {

    @Value("${open-api.url}")
    private String BASEURL;

    public List<DaeguResponseDto> fetch() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();

        String jsonString = restTemplate.getForObject(makeURL(), String.class);
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);

        JSONArray jsonData = (JSONArray) jsonObject.get("data");
        List<DaeguResponseDto> result = new ArrayList<>();

        for (Object o : jsonData) {
            JSONObject item = (JSONObject) o;
            result.add(makeLocationDto(item));
        }
        return result;
    }

    private URI makeURL() {
        return UriComponentsBuilder.fromHttpUrl(BASEURL)
                .queryParam("type", "json") // json 형태로 응답
                .build()
                .toUri();
    }

    private DaeguResponseDto makeLocationDto(JSONObject item) {
        DaeguResponseDto dto = DaeguResponseDto.builder()
                .cnt((String) item.get("cnt"))
                .FD_CS((String) item.get("FD_CS"))
                .OPENDATA_ID((String) item.get("OPENDATA_ID"))
                .SMPL_DESC((String) item.get("SMPL_DESC"))
                .build();
        return dto;
    }
}
