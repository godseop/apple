package org.godseop.apple.aop;

import lombok.extern.slf4j.Slf4j;

import org.godseop.apple.model.Error;
import org.godseop.apple.model.Result;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    // DB에러 예외처리
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public @ResponseBody ResponseEntity<Result> handleDatabaseException(
            final HttpServletRequest request,
            final HttpServletResponse response) {
        Result result = new Result();
        result.put(Error.DATABASE_ERROR);

        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // 서버에러 예외처리
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public @ResponseBody ResponseEntity<Result> handleException(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Exception exception) {
        printException(request, exception);

        Result result = new Result();
        result.put(exception);

        return new ResponseEntity<>(result, HttpStatus.SERVICE_UNAVAILABLE);
    }

    private void printException(HttpServletRequest request, Exception exception) {
        log.error("Request : " + request.getRequestURL() + " error occured " + exception);
        log.error("Parameters : {}", request.getParameterMap());

        for (StackTraceElement element : exception.getStackTrace()) {
            log.warn("{}", element);
        }
    }

}
