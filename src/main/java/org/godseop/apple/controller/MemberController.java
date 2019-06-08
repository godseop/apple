package org.godseop.apple.controller;

import org.godseop.apple.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/user")
public class MemberController {


    @Autowired
    private MemberService memberService;

    @GetMapping(value="/list")
    public ModelAndView viewUserListPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("user/userList");
        modelAndView.addObject("userList", memberService.getMemberListAll());

        return modelAndView;
    }

    @GetMapping(value="/list/{id}")
    public ModelAndView viewUserDetailPage(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("user/userDetail");
        modelAndView.addObject("user", memberService.getMember(id));

        return modelAndView;
    }

    @GetMapping(value="/reg")
    public ModelAndView viewUserRegPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("user/userReg");

        return modelAndView;
    }

}
