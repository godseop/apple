package org.godseop.apple.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.model.Result;
import org.godseop.apple.entity.User;
import org.godseop.apple.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(value="/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService  userService) {
        this.userService = userService;
    }


    @PostMapping(value="getUserListAll")
    public ResponseEntity<Result> getUserListAll() throws Exception {
        Result result = new Result();
        result.put("userList", userService.getUserListAll());
        TimeUnit.SECONDS.sleep(3);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping(value="getUserListAllJpa")
    public ResponseEntity<Result> getUserListAllJpa() throws Exception {
        Result result = new Result();
        result.put("userList", userService.getUserListAllJpa());
        TimeUnit.SECONDS.sleep(3);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value="json")
    public ResponseEntity<Result> testJson(@RequestBody User user) {
        //throw new AppleException(Error.SYSTEM_EXCEPTION);
        Result result = new Result();

        result.put("user", user);
        log.info("user : {}", user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="encoded")
    public ResponseEntity<Result> testEncoded(@ModelAttribute User user) {
        Result result = new Result();

        result.put("user", user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value="reg")
    public ResponseEntity<Result> registerUser(@RequestBody User user) {
        Result result = new Result();

        userService.registerUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
