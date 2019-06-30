package org.godseop.apple.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Dummy;
import org.godseop.apple.exception.AppleException;
import org.godseop.apple.model.Condition;
import org.godseop.apple.model.Error;
import org.godseop.apple.repository.mapper.DummyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
@PropertySource("classpath:rest.properties")
public class DummyService {

    @Value("${test.api.url}")
    private final String TEST_API_URL;

    private final DummyMapper dummyMapper;

    private final RestTemplate restTemplate;

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

    public Map testRestTemplate() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(TEST_API_URL)
                .queryParam("q", "isbn:0747532699");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Map> responseEntity =
                restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, Map.class);
        return responseEntity.getBody();
    }
}
