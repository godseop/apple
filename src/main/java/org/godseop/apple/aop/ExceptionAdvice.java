package org.godseop.apple.aop;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.exception.AppleException;
import org.godseop.apple.model.Error;
import org.godseop.apple.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    // 비즈니스 예외처리(로직상 예외처리)
    @ExceptionHandler({AppleException.class})
    public @ResponseBody ResponseEntity<Result> handleAppleException(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AppleException exception) {

        Result result = new Result();
        result.put(exception);
        log.error("Apple Error occured... [{}] {}", exception.getCode(), exception.getMessage());
        log.error("{}", (Object) exception.getStackTrace());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 시스템에러 예외처리
    @ExceptionHandler({SystemException.class})
    public ModelAndView handleSystemException(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final SystemException exception) {

        log.error("System Error occured... [{}] {}", exception.getClass(), exception.getMessage());
        log.error("{}", (Object) exception.getStackTrace());

        ModelAndView modelAndView = new ModelAndView();
        if (isAjaxRequest(request)) {
            modelAndView.setViewName("jsonView");
            modelAndView.addObject("result", new Result(Error.SERVICE_UNAVAILABLE));
        } else {
            modelAndView.setViewName("error/503");
            modelAndView.addObject("message", exception.getMessage());
        }

        return modelAndView;
    }

    // 예외처리(그 외)
    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Exception exception) {

        log.error("Intenal Server Error occured... [{}] {}", exception.getClass(), exception.getMessage());
        log.error("{}", (Object) exception.getStackTrace());

        ModelAndView modelAndView = new ModelAndView();
        if (isAjaxRequest(request)) {
            modelAndView.setViewName("jsonView");
            modelAndView.addObject("result", new Result(Error.INTERNAL_SERVER_ERROR));
        } else {
            modelAndView.setViewName("error/500");
            modelAndView.addObject("message", exception.getMessage());
        }

        return modelAndView;
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWithHeader = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equalsIgnoreCase(requestedWithHeader);
    }

}
