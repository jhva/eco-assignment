package com.api.ecoassignment.domain.daegu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DaeguResponseDto {

    private String cnt;
    private String OPENDATA_ID;
    private String FD_CS;
    private String SMPL_DESC;
}
