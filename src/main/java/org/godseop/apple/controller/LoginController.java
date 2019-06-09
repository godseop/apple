package org.godseop.apple.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class LoginController {

    @GetMapping(value="/login")
    public ModelAndView viewLoginPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referer);
        modelAndView.setViewName("public/login");

        return modelAndView;
    }

    @GetMapping(value="/logout")
    public ModelAndView viewLogoutPage() {
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

    @GetMapping(value="/home")
    public ModelAndView viewHomePage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("home/home");

        return modelAndView;
    }

}
