package org.godseop.apple.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Member;
import org.godseop.apple.model.Result;
import org.godseop.apple.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(value="/user")
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping(value="getUserListAll")
    public ResponseEntity<Result> getUserListAll() throws Exception {
        Result result = new Result();
        result.put("userList", memberService.getMemberListAll());
        TimeUnit.SECONDS.sleep(3);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping(value="getUserListAllJpa")
    public ResponseEntity<Result> getUserListAllJpa() throws Exception {
        Result result = new Result();
        result.put("userList", memberService.getMemberListAllJpa());
        TimeUnit.SECONDS.sleep(3);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value="json")
    public ResponseEntity<Result> testJson(@RequestBody Member member) {
        //throw new AppleException(Error.SYSTEM_EXCEPTION);
        Result result = new Result();

        result.put("member", member);
        log.info("member : {}", member);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="encoded")
    public ResponseEntity<Result> testEncoded(@ModelAttribute Member member) {
        Result result = new Result();

        result.put("member", member);
        log.info("member : {}", member);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value="reg")
    public ResponseEntity<Result> registerUser(@RequestBody Member member) {
        Result result = new Result();

        memberService.registerMember(member);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
