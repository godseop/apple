package org.godseop.apple.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Member;
import org.godseop.apple.model.Result;
import org.godseop.apple.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value="/member")
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value="list")
    public ResponseEntity<Result> getUserList() {
        Result result = new Result();
        result.put("memberList", memberService.getMemberList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="all")
    public ResponseEntity<Result> getUserListAll() {
        Result result = new Result();
        result.put("memberList", memberService.getMemberListAll());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "profile")
    public ResponseEntity<Result> getMember(@RequestBody Member member) {
        Result result = new Result();
        result.put("memberList", memberService.findMember(member.getUid()));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="modify")
    public ResponseEntity<Result> modifyUser(@RequestBody Member member) {
        Result result = new Result();
        memberService.modifyMember(member);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value = "withdraw")
    public ResponseEntity<Result> withdrawUser(@RequestBody Member member) {
        Result result = new Result();
        log.warn("member request to withdraw... {}", member);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
