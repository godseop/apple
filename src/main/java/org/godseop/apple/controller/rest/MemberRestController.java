package org.godseop.apple.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.mysql.Member;
import org.godseop.apple.model.Result;
import org.godseop.apple.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/member")
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping(value="/list")
    public ResponseEntity<Result> getMemberList() {
        Result result = new Result();

        result.put("memberList", memberService.getMemberList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/all")
    public ResponseEntity<Result> getMemberListAll() {
        Result result = new Result();

        result.put("memberList", memberService.getMemberListAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/profile")
    public ResponseEntity<Result> getMember(@RequestBody Member member) {
        Result result = new Result();

        result.put("memberList", memberService.getMember(member.getUid()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/modify")
    public ResponseEntity<Result> modifyMember(@RequestBody Member member) {
        Result result = new Result();

        memberService.modifyMember(member);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/withdraw")
    public ResponseEntity<Result> withdrawMember(@RequestBody Member member) {
        Result result = new Result();

        log.warn("member request to withdraw... {}", member);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
