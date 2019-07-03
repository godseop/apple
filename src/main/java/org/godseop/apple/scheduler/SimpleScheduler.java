package org.godseop.apple.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SimpleScheduler {

    @Scheduled(fixedDelay = 30000)
    public void batchTest() {
        log.debug("==========BATCH START============");
    }


    @Scheduled(cron = "0 0/1 * * * *")
    public void batchTest2() {
        log.debug("==========BATCH START2============");
    }
}
