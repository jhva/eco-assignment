package com.api.ecoassignment.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 오류 메시지와 상태를 쉽게 추가하기 위한 Enum
 */
@Getter
public enum ErrorCode {

    SPECIFIC_EMPLOYEE_NOT_FOUND("해당 사원을 찾을 수 없습니다. ", HttpStatus.BAD_REQUEST),
    DEPARTMENT_NOT_FOUND("해당 부서를 찾을 수 없습니다. ", HttpStatus.BAD_REQUEST),
    CAN_NOT_SALARY_INCREASE("최저 %d 최고 d% 사이로 맞춰주세요 ", HttpStatus.BAD_REQUEST);

    //오류 메시지
    private final String message;
    //오류 상태코드
    private final HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
