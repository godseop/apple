package org.godseop.apple.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Dummy;
import org.godseop.apple.exception.AppleException;
import org.godseop.apple.entity.Condition;
import org.godseop.apple.external.github.GitHubRest;
import org.godseop.apple.model.Error;
import org.godseop.apple.repository.mapper.DummyMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
@PropertySource("classpath:rest.properties")
public class DummyService {

    private final DummyMapper dummyMapper;

    private final GitHubRest gitHubRest;

    @Value("${test.api.url}")
    private String TEST_API_URL;

    public int getDummyListCount(Condition condition) {
        return dummyMapper.selectDummyListCount(condition);
    }

    public List<Dummy> getDummyList(Condition condition) {
        return dummyMapper.selectDummyList(condition);
    }

    public Dummy getDummy(String id) {
        return dummyMapper.selectDummy(id);
    }

    public void saveDummy(Dummy dummy) {
        if (dummyMapper.insertDummy(dummy) != 1) {
            throw new AppleException(Error.WRONG_USER_INPUT);
        }
    }

    public void modifyDummy(Dummy dummy) {
        if (dummyMapper.updateDummy(dummy) != 1) {
            throw new AppleException(Error.WRONG_USER_INPUT);
        }
    }

    public void mergeDummy(Dummy dummy) {
        log.error("count: {}", dummyMapper.upsertDummy(dummy));
    }

    public void removeDummy(Long id) {
        if (dummyMapper.deleteDummy(id) != 1) {
            throw new AppleException(Error.WRONG_USER_INPUT);
        }
    }

    public Map testRestTemplate(String id) {
        //Map map = gitHubRest.getSingleUser(id);
        Map map = gitHubRest.updateSingleUser();
        log.debug("{}", map);
        return map;
//        URI uri = UriComponentsBuilder
//                .newInstance().scheme("https").host("www.googleapis.com").port("443")
//                .path("/books/{version}/volumes")
//                .queryParam("q", "isbn:{isbn}")
//                .build().expand("v1", "0747532699").encode().toUri();
//
////        UriComponentsBuilder builder = UriComponentsBuilder
////                .fromHttpUrl(TEST_API_URL)
////                .port("8080")
////                .queryParam("q", "isbn:0747532699");
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//
//        MultiValueMap<String, String> httpBody = new LinkedMultiValueMap<>();
//        httpBody.add("requestParameter1", "hello");
//        httpBody.add("requestParameter2", "world");
//
//        HttpEntity<Map> httpEntity = new HttpEntity<>(httpBody, httpHeaders);
//
//        ResponseEntity<Map> responseEntity =
//                defaultRestTeamplate.exchange(uri, HttpMethod.GET, httpEntity, Map.class);
//        return responseEntity.getBody();
    }
}
