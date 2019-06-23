package org.godseop.apple.aop;

import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.exception.AppleException;
import org.godseop.apple.model.Error;
import org.godseop.apple.model.Result;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;
import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    // 비즈니스 예외처리(로직상 예외처리-응답은 200으로 정상으로 나감)
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

    // 시스템에러 예외처리(예상되는 예외처리 랩핑)
    @ExceptionHandler({SystemException.class})
    public ModelAndView handleSystemException(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Exception exception) {

        log.error("System Error occured... {}", exception.getMessage());
        // SYSTEM EXCEPTION SOME LOGICS HERE...
        ModelAndView modelAndView = new ModelAndView();

        if (isAjaxRequest(request)) {
            modelAndView.setViewName("jsonView");
            modelAndView.addObject("result", new Result(Error.INTERNAL_SERVER_ERROR).get("result"));
        } else {
            modelAndView.setViewName("/error/503");
            request.setAttribute("message", exception.getMessage());
        }

        return modelAndView;
    }

    // DB에러 예외처리
    @ExceptionHandler({DataAccessException.class, SQLException.class})
    public void handleDatabaseException(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Exception exception) {

        log.error("Database Error occured... {}", exception.getMessage());
        // DATABASE EXCEPTION SOME LOGICS HERE...

        this.handleException(request, response, exception);
    }

    // 예외처리(예상치못한 예외케이스)
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

}
