package com.arsgsg.URLShortener.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ShortUrl {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String originUrl;
    @Column(nullable = false)
    private String shortUrl;
    protected ShortUrl(){}
    public ShortUrl(String originUrl, String shortUrl){
        this.originUrl = originUrl;
        this.shortUrl = shortUrl;
    }
}
