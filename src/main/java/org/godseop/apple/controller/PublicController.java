package org.godseop.apple.controller;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.entity.Member;
import org.godseop.apple.model.Result;
import org.godseop.apple.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class PublicController {

    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value="/login")
    public ModelAndView viewLoginPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referer);
        log.info("REFERER : {}", referer); // 주소 직접 입력 or 즐겨찾기로 접근시 NULL

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
