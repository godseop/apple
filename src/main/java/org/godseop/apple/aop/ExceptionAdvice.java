package org.godseop.apple.aop;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.exception.AppleException;
import org.godseop.apple.model.Error;
import org.godseop.apple.model.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    // 비즈니스 예외처리
    @ExceptionHandler({AppleException.class})
    public @ResponseBody ResponseEntity<Result> handleAppleException(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AppleException exception) {
        Result result = new Result();
        result.put(exception);
        exception.printStackTrace();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // DB에러 예외처리
    @ExceptionHandler({DataAccessException.class, SQLException.class})
    public ModelAndView handleDatabaseException(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Exception exception) {

        log.error("Database Error occured... {}", exception.getMessage());
        exception.printStackTrace();

        ModelAndView modelAndView = new ModelAndView();

        if (isAjaxRequest(request)) {
            modelAndView.setViewName("jsonView");
            modelAndView.addObject("result", new Result(Error.DATABASE_ERROR).get("result"));
        } else {
            modelAndView.setViewName("/error/database");
            modelAndView.addObject("message", exception.getMessage());
        }

        return modelAndView;
    }

    // 서버에러 예외처리
    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Exception exception) {

        log.error("Intenal Server Error occured... {}", exception.getMessage());
        exception.printStackTrace();

        ModelAndView modelAndView = new ModelAndView();

        if (isAjaxRequest(request)) {
            modelAndView.setViewName("jsonView");
            modelAndView.addObject("result", new Result(Error.INTERNAL_SERVER_ERROR).get("result"));
        } else {
            modelAndView.setViewName("/error/500");
            request.setAttribute("message", exception.getMessage());
        }

        return modelAndView;
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWithHeader = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(requestedWithHeader);
    }

    @Bean
    public MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }

}
