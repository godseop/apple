package org.godseop.apple.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.model.Result;
import org.godseop.apple.entity.User;
import org.godseop.apple.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(value="/user")
public class UserRestController {

    // NOT RECOMMENDED : Field Dependency Injection
    //@Autowired
    //private UserService userService;

    // SpringTeam RECOMMENDED : Constructor Dependency Injection
    private final UserService userService;

    public  UserRestController(UserService  userService) {
        this.userService = userService;
    }


    @PostMapping(value="getUserListAll")
    public Result getUserListAll() throws Exception {
        Result result = new Result();
        result.put("userList", userService.getUserListAll());
        TimeUnit.SECONDS.sleep(3);
        return result;
    }
    
    @PostMapping(value="getUserListAllJpa")
    public Result getUserListAllJpa() throws Exception {
        Result result = new Result();
        result.put("userList", userService.getUserListAllJpa());
        TimeUnit.SECONDS.sleep(3);
        return result;
    }


    @PostMapping(value="json")
    public Result testJson(@RequestBody User user) throws Exception {
        //throw new AppleException(Error.SYSTEM_EXCEPTION);
        Result result = new Result();

        result.put("user", user);
        log.info("user : {}", user);
        return result;
    }

    @PostMapping(value="encoded")
    public Result testEncoded(@ModelAttribute User user) {
        Result result = new Result();

        result.put("user", user);
        log.info("user : {}", user);
        return result;
    }


    @PostMapping(value="reg")
    public Result registerUser(@RequestBody User user) throws Exception {
        Result result = new Result();

        userService.registerUser(user);

        return result;
    }
}
