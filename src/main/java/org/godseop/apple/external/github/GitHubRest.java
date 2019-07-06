package org.godseop.apple.external.github;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class GitHubRest {

    private final RestTemplate defaultRestTeamplate;

    public Map getSingleUser(String githubId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(GitHubApi.GET_SINGLE_USER.URL)
                .build().expand(githubId).encode().toUri();

//        UriComponentsBuilder builder = UriComponentsBuilder
//                .fromHttpUrl(TEST_API_URL)
//                .port("8080")
//                .queryParam("q", "isbn:0747532699");

        HttpHeaders httpHeaders = buildHttpHeaders();
        HttpEntity<Map> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Map> responseEntity =
                defaultRestTeamplate.exchange(uri, GitHubApi.GET_SINGLE_USER.METHOD, httpEntity, Map.class);

        log.debug("{}", responseEntity.getBody());
        return responseEntity.getBody();
    }

    public Map updateSingleUser() {
        URI uri = UriComponentsBuilder.fromHttpUrl(GitHubApi.UPDATE_USER.URL)
                .build().encode().toUri();


        HttpHeaders httpHeaders = buildHttpHeaders();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("name", "godseop");
        body.add("email", "godseop@gmail.com");
        body.add("blog", "https://");
        body.add("company", "");
        body.add("location", "");
        body.add("hireable", true);
        body.add("bio", "hello, world");

        HttpEntity<Map> httpEntity = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<Map> responseEntity =
                defaultRestTeamplate.exchange(uri, GitHubApi.UPDATE_USER.METHOD, httpEntity, Map.class);

        log.debug("{}", responseEntity.getBody());
        return responseEntity.getBody();
    }

    private HttpHeaders buildHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return httpHeaders;
    }

}
