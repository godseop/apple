package org.godseop.apple.external.google;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:rest.properties")
public class GoogleRest {

    private final RestTemplate defaultRestTeamplate;

    public Map getBookInfoByIsbn(String isbn) {
                URI uri = UriComponentsBuilder
                .newInstance().scheme("https").host("www.googleapis.com").port("443")
                .path("/books/{version}/volumes")
                .queryParam("q", "isbn:{isbn}")
                .build().expand("v1", isbn).encode().toUri();

//        UriComponentsBuilder builder = UriComponentsBuilder
//                .fromHttpUrl(TEST_API_URL)
//                .port("8080")
//                .queryParam("q", "isbn:0747532699");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        MultiValueMap<String, String> httpBody = new LinkedMultiValueMap<>();
        httpBody.add("requestParameter1", "hello");
        httpBody.add("requestParameter2", "world");

        HttpEntity<Map> httpEntity = new HttpEntity<>(httpBody, httpHeaders);

        ResponseEntity<Map> responseEntity =
                defaultRestTeamplate.exchange(uri, HttpMethod.GET, httpEntity, Map.class);
        return responseEntity.getBody();
    }
}
