package org.godseop.apple.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/dummy")
public class DummyController {

    @GetMapping(value="")
    public ModelAndView viewDummyListPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("dummy/dummy");
        return modelAndView;
    }

    @GetMapping(value="/chat")
    public ModelAndView viewChatPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("dummy/chat");
        return modelAndView;
    }

    @GetMapping(value="/video")
    public ModelAndView viewVideoPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("dummy/video");
        return modelAndView;
    }

    @GetMapping(value="/chart")
    public ModelAndView viewChartPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("dummy/chart");
        return modelAndView;
    }

}
