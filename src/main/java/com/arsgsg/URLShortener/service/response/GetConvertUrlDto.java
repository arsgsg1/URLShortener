package com.arsgsg.URLShortener.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetConvertUrlDto {
    private String originUrl;
    private String convertedUrl;
}
