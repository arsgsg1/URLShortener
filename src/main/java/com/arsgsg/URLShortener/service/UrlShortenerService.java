package com.arsgsg.URLShortener.service;

import com.arsgsg.URLShortener.domain.ShortUrl;
import com.arsgsg.URLShortener.repository.ShortUrlRepository;
import com.arsgsg.URLShortener.service.request.ConvertShortUrlDto;
import com.arsgsg.URLShortener.util.UrlConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {
    private final ShortUrlRepository repository;
    private final UrlConverter urlConverter;

    @Transactional(readOnly = true)
    public String getOriginUrl(String shortUrl){
        return repository.findByShortUrl(shortUrl).
                orElseThrow(() -> new IllegalArgumentException("url이 없습니다.")).getOriginUrl();
    }
    @Transactional
    public String convertShortUrl(ConvertShortUrlDto dto) {
        String resultUrl;
        Optional<ShortUrl> findUrl = repository.findByOriginUrl(dto.getOriginUrl());
        if (findUrl.isPresent()) {
            resultUrl = urlConverter.concatUrl(findUrl.get().getShortUrl());
        }
        else {
            int urlCount = repository.countUrls().intValue();
            repository.save(new ShortUrl(dto.getOriginUrl(), urlConverter.convertId2ShortUrl(urlCount + 1)));
            resultUrl = urlConverter.getEncodingUrl(urlCount + 1);
        }
        return resultUrl;
    }
}
