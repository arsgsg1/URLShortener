package com.arsgsg.URLShortener.presentation.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INVALID_INPUT_URL(HttpStatus.BAD_REQUEST, "U001", "잘못된 URL 형식입니다."),
    NO_SUCH_RESTORED_URL(HttpStatus.BAD_REQUEST, "U002", "저장되지 않은 축약 URL입니다.");

    private HttpStatus httpStatus;
    private String code;
    private String message;

    ErrorCode(final HttpStatus httpStatus, final String code, final String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
