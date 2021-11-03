package com.arsgsg.URLShortener.repository;

import com.arsgsg.URLShortener.domain.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findByOriginUrl(String originUrl);
    Optional<ShortUrl> findByShortUrl(String shortUrl);
    @Query("SELECT COUNT(shortUrl.id) FROM ShortUrl shortUrl")
    Long countUrls();
}
