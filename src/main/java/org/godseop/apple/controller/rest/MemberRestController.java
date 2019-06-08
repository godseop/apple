package org.godseop.apple.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Member;
import org.godseop.apple.model.Result;
import org.godseop.apple.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(value="/user")
public class MemberRestController {

    // NOT RECOMMENDED : Field Dependency Injection
    //@Autowired
    //private UserService userService;

    // SpringTeam RECOMMENDED : Constructor Dependency Injection
    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping(value="getUserListAll")
    public Result getUserListAll() throws Exception {
        Result result = new Result();
        result.put("userList", memberService.getMemberListAll());
        TimeUnit.SECONDS.sleep(3);
        return result;
    }
    
    @PostMapping(value="getUserListAllJpa")
    public Result getUserListAllJpa() throws Exception {
        Result result = new Result();
        result.put("userList", memberService.getMemberListAllJpa());
        TimeUnit.SECONDS.sleep(3);
        return result;
    }


    @PostMapping(value="json")
    public Result testJson(@RequestBody Member member) throws Exception {
        //throw new AppleException(Error.SYSTEM_EXCEPTION);
        Result result = new Result();

        result.put("member", member);
        log.info("member : {}", member);
        return result;
    }

    @PostMapping(value="encoded")
    public Result testEncoded(@ModelAttribute Member member) {
        Result result = new Result();

        result.put("member", member);
        log.info("member : {}", member);
        return result;
    }


    @PostMapping(value="reg")
    public Result registerUser(@RequestBody Member member) throws Exception {
        Result result = new Result();

        memberService.registerMember(member);

        return result;
    }
}
