package org.godseop.apple;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.model.User;
import org.godseop.apple.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserServiceTests {

    @Autowired
    UserService userService;


    @Test
    public void getAllUser() {
        List<User> userList = userService.getUserListAll();
        log.info("users : {}", userList);
    }

}
