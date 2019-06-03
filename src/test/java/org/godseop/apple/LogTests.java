package org.godseop.apple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class LogTests {

	@Test
    public void logTests() {
		log.error("error");
        log.warn("warn");
        log.info("info");
        log.trace("trace");
        log.debug("debug");
    }
	
}
