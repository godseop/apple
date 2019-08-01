package org.godseop.apple.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Member;
import org.godseop.apple.model.Result;
import org.godseop.apple.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PublicController {

    private final MemberService memberService;

    @GetMapping(value="/login")
    public ModelAndView viewLoginPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("public/login");
        return modelAndView;
    }

    @GetMapping(value="/join")
    public ModelAndView viewMemberJoinPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("public/join");
        return modelAndView;
    }

    @PostMapping(value="/join")
    public ResponseEntity<Result> registMember(@RequestBody Member member) {
        Result result = new Result();

        memberService.registerMember(member);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value="/home")
    public ModelAndView viewHomePage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("home/home");
        return modelAndView;
    }

}
