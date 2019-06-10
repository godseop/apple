package org.godseop.apple.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String viewLogoutPage(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/login";
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
