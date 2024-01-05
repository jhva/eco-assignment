package com.api.ecoassignment.global.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DepartmentEnum {
    UPDATE_MSG("업데이트가 완료 되었습니다."),
    ERROR_MSG("업데이트 %d개 완료되지 않았습니다.");
    private String msg;
}
