package org.godseop.apple.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "503 SERVICE_UNAVAILABLE")
public class SystemException extends RuntimeException {
    public SystemException(Exception exception) {
        super(exception);
    }
}
