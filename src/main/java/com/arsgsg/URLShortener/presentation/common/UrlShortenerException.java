package com.arsgsg.URLShortener.presentation.common;

public class UrlShortenerException extends RuntimeException{
    private ErrorCode errorCode;

    public UrlShortenerException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    public ErrorCode getErrorCode(){
        return this.errorCode;
    }
}
