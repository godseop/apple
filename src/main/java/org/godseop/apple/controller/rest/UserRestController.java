package org.godseop.apple.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.model.User;
import org.godseop.apple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(value="/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping(value="getUserListAll")
    public List<User> getUserListAll() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return userService.getUserListAll();
    }
    
    @PostMapping(value="getUserListAllJpa")
    public List<User> getUserListAllJpa() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return userService.getUserListAllJpa();
    }


    @PostMapping(value="json")
    public User testJson(@RequestBody User user) throws Exception {
        TimeUnit.SECONDS.sleep(3);
        log.info("user : {}", user);
        return user;
    }

    @PostMapping(value="encoded")
    public User testEncoded(@ModelAttribute User user) {
        log.info("user : {}", user);
        return user;
    }

}
