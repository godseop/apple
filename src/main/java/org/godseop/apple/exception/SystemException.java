package org.godseop.apple.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "OMG! Server dying...T_T")
public class SystemException extends RuntimeException {
}
