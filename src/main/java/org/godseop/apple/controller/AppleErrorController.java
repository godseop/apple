package org.godseop.apple.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


@Slf4j
@Controller
public class AppleErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "error";
    }

    @GetMapping("error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/403";
            } else if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            } else if(statusCode == HttpStatus.SERVICE_UNAVAILABLE.value()) {
                return "error/503";
            }
        }
        // HttpStatus.INTERNAL_SERVER_ERROR
        return "error/500";
    }



    @GetMapping(value="error/403")
    public ModelAndView viewForbiddenErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/403");

        return modelAndView;
    }

    @GetMapping(value="error/404")
    public ModelAndView viewNotFoundErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404");

        return modelAndView;
    }

    @GetMapping(value="error/503")
    public ModelAndView viewServiceUnavailableErrorPage(@RequestAttribute("exception") String exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/503");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

    @GetMapping(value="error/500")
    public ModelAndView viewInternalServerErrorPage(@RequestAttribute("exception") String exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/500");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

}
