package org.godseop.apple.controller.rest;

import org.godseop.apple.model.User;
import org.godseop.apple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping(value="getUserListAll")
    public List<User> getUserListAll() {
        return userService.getUserListAll();
    }
    
    @GetMapping(value="getUserListAllJpa")
    public List<User> getUserListAllJpa() {
        return userService.getUserListAllJpa();
    }

}
