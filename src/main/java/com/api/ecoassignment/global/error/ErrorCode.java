package com.api.ecoassignment.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 오류 메시지와 상태를 쉽게 추가하기 위한 Enum
 */
@Getter
public enum ErrorCode {

    SPECIFIC_EMPLOYEE_NOT_FOUND("해당 사원을 찾을 수 없습니다. ", HttpStatus.BAD_REQUEST);

    //오류 메시지
    private final String message;
    //오류 상태코드
    private final HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

}