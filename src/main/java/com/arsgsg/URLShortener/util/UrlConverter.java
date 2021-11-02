package com.arsgsg.URLShortener.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlConverter {
    @Value("${server.host}")
    private String baseUrl;
    @Value("${server.port}")
    private String port;
    private final char[] BASE62 = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public String convertId2ShortUrl(int n){
        StringBuilder sb = new StringBuilder("");

        while (n > 0)
        {
            sb.append(BASE62[n % 62]);
            n = n / 62;
        }

        return sb.reverse().toString();
    }

    public String getEncodingUrl(int n){
        String encodingUrl = convertId2ShortUrl(n);
        return concatUrl(encodingUrl);
    }
    public String concatUrl(String url){
        return "http://" + baseUrl + ":" + port + "/api/" + url;
    }
}
