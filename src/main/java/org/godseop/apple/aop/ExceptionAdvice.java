package org.godseop.apple.aop;

import lombok.extern.slf4j.Slf4j;

import org.godseop.apple.model.Error;
import org.godseop.apple.model.Result;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    // DB에러 예외처리
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public @ResponseBody Result handleDatabaseException(//final HttpServletRequest request,
                                                        //final HttpServletResponse response
                                                        ) {
        Result result = new Result();
        result.put(Error.DATABASE_ERROR);

        return result;
    }


    // 서버에러 예외처리
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public @ResponseBody Result handleException(final HttpServletRequest request,
                                             // final HttpServletResponse response,
                                                final Exception exception) {
        printException(request, exception);

        Result result = new Result();
        result.put(exception);

        return result;
    }

    private void printException(HttpServletRequest req, Exception exception) {
        log.error("Request : " + req.getRequestURL() + " error occured " + exception);
        log.error("Parameters : {}", req.getParameterMap());

        for (StackTraceElement element : exception.getStackTrace()) {
            log.error("{}", element);
        }
    }

}
