package com.arsgsg.URLShortener.controller;

import com.arsgsg.URLShortener.service.UrlShortenerService;
import com.arsgsg.URLShortener.service.request.ConvertShortUrlDto;
import com.arsgsg.URLShortener.service.response.GetConvertUrlDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;

    @PostMapping("/api")
    public ResponseEntity convertShortURL(@Valid @RequestBody ConvertShortUrlDto dto){
        String convertedUrl = urlShortenerService.convertShortUrl(dto);
        GetConvertUrlDto resultDto = new GetConvertUrlDto(dto.getOriginUrl(), convertedUrl);
        return ResponseEntity.ok(resultDto);
    }
    @GetMapping("/api/{shortUrl}")
    public void redirectUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        String originUrl = urlShortenerService.getOriginUrl(shortUrl);
        response.sendRedirect(originUrl);
    }
}
