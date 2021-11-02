package com.arsgsg.URLShortener.service.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConvertShortUrlDto {
    @URL(message = "URL이 형식에 맞지 않습니다.")
    @NotEmpty(message = "URL은 빈 문자열이 될 수 없습니다.")
    private String originUrl;
}
