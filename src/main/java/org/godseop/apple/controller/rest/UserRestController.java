package org.godseop.apple.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.model.User;
import org.godseop.apple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value="/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping(value="getUserListAll")
    public List<User> getUserListAll() {
        return userService.getUserListAll();
    }
    
    @PostMapping(value="getUserListAllJpa")
    public List<User> getUserListAllJpa() {
        return userService.getUserListAllJpa();
    }


    @PostMapping(value="json")
    public User testJson(@RequestBody User user) {
        log.info("user : {}", user);
        return user;
    }

    @PostMapping(value="encoded")
    public List<User> testEncoded(@ModelAttribute List<User> userList) {
        log.info("userList : {}", userList);
        return userList;
    }

}
