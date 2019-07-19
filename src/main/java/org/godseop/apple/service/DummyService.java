package org.godseop.apple.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Condition;
import org.godseop.apple.entity.Dummy;
import org.godseop.apple.exception.AppleException;
import org.godseop.apple.rest.github.GitHubRest;
import org.godseop.apple.rest.google.GoogleRest;
import org.godseop.apple.model.Error;
import org.godseop.apple.mapper.DummyMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DummyService {

    private final DummyMapper dummyMapper;

    private final GitHubRest gitHubRest;

    private final GoogleRest googleRest;

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
         dummyMapper.upsertDummy(dummy);
    }

    public void removeDummy(Long id) {
        if (dummyMapper.deleteDummy(id) != 1) {
            throw new AppleException(Error.WRONG_USER_INPUT);
        }
    }

    public Map getBookInfo(String isbn) {
        return googleRest.getBookInfoByIsbn(isbn);
    }

    public Map getGithubUserInfo(String githubId) {
        return gitHubRest.getSingleUser(githubId);
    }


    @Scheduled(fixedDelay = 30000)
    public void batchTest() {
        log.debug("==========BATCH START============");
    }


    @Scheduled(cron = "0 0/1 * * * *")
    public void batchTest2() {
        log.debug("==========BATCH START2============");
    }


}
