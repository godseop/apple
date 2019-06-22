package org.godseop.apple.controller;

import lombok.RequiredArgsConstructor;
import org.godseop.apple.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value="/list")
    public ModelAndView viewMemberListPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("member/memberList");
        modelAndView.addObject("memberList", memberService.getMemberList());

        return modelAndView;
    }

    @GetMapping(value="/detail/{uid}")
    public ModelAndView viewMemberDetailPage(@PathVariable("uid") String uid) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("member/memberDetail");
        modelAndView.addObject("member", memberService.getMember(uid));

        return modelAndView;
    }


}
