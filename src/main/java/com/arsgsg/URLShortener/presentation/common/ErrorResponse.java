package com.arsgsg.URLShortener.presentation.common;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {
    private String message;
    private List<FieldErrorCustom> errors = new ArrayList<>();
    private String code;

    public ErrorResponse(BindingResult bindingResult) {
        for (FieldError e : bindingResult.getFieldErrors()) {
            this.errors.add(new FieldErrorCustom(e.getField(), e.getRejectedValue(), e.getDefaultMessage()));
        }
    }

    public ErrorResponse(ErrorCode invalidInputValue, String errorMessage) {
        this.code = invalidInputValue.getCode();
        this.message = errorMessage;
    }

    @Getter
    public static class FieldErrorCustom{
        private String field;
        private String value;
        private String reason;

        protected FieldErrorCustom(){}
        public FieldErrorCustom(String field, Object rejectedValue, String defaultMessage) {
            this.field = field;
            this.value = (String) rejectedValue;
            this.reason = defaultMessage;
        }
    }
}
