package org.godseop.apple.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.godseop.apple.entity.Condition;
import org.godseop.apple.entity.Dummy;
import org.godseop.apple.exception.AppleException;
import org.godseop.apple.rest.github.GitHubRest;
import org.godseop.apple.rest.google.GoogleRest;
import org.godseop.apple.model.Error;
import org.godseop.apple.mapper.DummyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DummyService {

    private final DummyMapper dummyMapper;

    private final GitHubRest gitHubRest;

    private final GoogleRest googleRest;

    private final TaskScheduler taskScheduler;

    private static Map<String, ScheduledFuture<?>> scheduledFutureMap = new ConcurrentHashMap<>();


    public int getDummyListCount(Condition condition) {
        return dummyMapper.selectDummyListCount(condition);
    }

    public List<Dummy> getDummyList(Condition condition) {
        return dummyMapper.selectDummyList(condition);
    }

    public List<Dummy> getDummy(Dummy dummy) {
        return dummyMapper.selectDummy(dummy);
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
        //log.debug("==========BATCH START============");
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void batchTest2() {
        //log.debug("==========BATCH START2============");
    }

    public void triggerBatchSomething() {
        Dummy dummy = Dummy.builder()
                .id(UUID.randomUUID().toString())
                .name(RandomStringUtils.random(10, true, false))
                .build();

        ScheduledFuture<?> scheduledFuture = taskScheduler.scheduleAtFixedRate(batchSomething(dummy), 3000);

        scheduledFutureMap.put(dummy.getId(), scheduledFuture);
    }


    private Runnable batchSomething(Dummy dummy) {
        return () -> {
            String uuid = UUID.randomUUID().toString();
            log.debug("TASK ID:{} NAME:{} Run...", dummy.getId(), dummy.getName());
            if (uuid.startsWith("a")) {
                log.debug("TASK ID:{} finally stoped!! lucky {}", dummy.getId(), uuid);
                ScheduledFuture<?> scheduledFuture =scheduledFutureMap.get(dummy.getId());
                scheduledFuture.cancel(true);
            }
        };
    }

}
