package org.godseop.apple.controller;

import org.godseop.apple.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping(value="/list")
    public ModelAndView viewUserListPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("member/memberList");
        modelAndView.addObject("memberList", memberService.getMemberList());

        return modelAndView;
    }

    @GetMapping(value="/detail/{uid}")
    public ModelAndView viewUserDetailPage(@PathVariable("uid") String uid) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("member/memberDetail");
        modelAndView.addObject("member", memberService.findMember(uid));

        return modelAndView;
    }


}
