package com.arsgsg.URLShortener.IntegrationTest;

import com.arsgsg.URLShortener.domain.ShortUrl;
import com.arsgsg.URLShortener.repository.ShortUrlRepository;
import com.arsgsg.URLShortener.service.request.ConvertShortUrlDto;
import com.arsgsg.URLShortener.service.response.GetConvertUrlDto;
import com.arsgsg.URLShortener.util.UrlConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlShortenerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ShortUrlRepository repository;
    @Autowired
    private UrlConverter converter;
    private final String storedUrl = "http://www.naver.com";
    private final String requestUrl = "http://www.google.com";
    private String convertShortUrl;
    @BeforeEach
    public void setup(){
        convertShortUrl = converter.convertId2ShortUrl(1);
        ShortUrl shortUrl = new ShortUrl(storedUrl, convertShortUrl);
        repository.save(shortUrl);
    }
    @AfterEach //다음 테스트에 영향이 가지 않게 하기 위함.
    public void tearDown() throws Exception{
        System.out.println("====tear down start========");
        repository.deleteAll();
    }
    @Test
    public void createConvertUrlTest(){
        //given
        String testResultUrl = converter.getEncodingUrl(repository.countUrls().intValue() + 1);
        ConvertShortUrlDto requestDto = new ConvertShortUrlDto(requestUrl);
        String url = "http://localhost:" + port + "/api";
        //when
        ResponseEntity<GetConvertUrlDto> responseEntity = restTemplate.postForEntity(url, requestDto, GetConvertUrlDto.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getConvertedUrl()).isEqualTo(testResultUrl);
    }
    @Test
    public void readOriginUrlTest(){
        //given
        String url = "http://localhost:" + port + "/api/" + convertShortUrl;
        //when
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        //then
        assertThat(responseEntity.getHeaders().getLocation().toString().contains(storedUrl.substring(7))).isTrue();
    }
}
