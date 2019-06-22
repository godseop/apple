package org.godseop.apple.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
@RequestMapping(value="/error")
public class ErrorController {

    @GetMapping(value="/403")
    public ModelAndView viewForbiddenErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/403");

        return modelAndView;
    }

    @GetMapping(value="/404")
    public ModelAndView viewNotFoundErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404");

        return modelAndView;
    }

    @GetMapping(value="/500")
    public ModelAndView viewInternalServerErrorPage(@RequestAttribute("exception") String exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/500");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

    @GetMapping(value="/database")
    public ModelAndView viewDatabaseErrorPage(@RequestAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/database");
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @GetMapping(value="/test")
    public void testResponseStatusException() {
        try {
            log.error("some code here that exception throwable...");
        } catch(Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Testing ResponseStatusException", exception);
        }
    }
}
