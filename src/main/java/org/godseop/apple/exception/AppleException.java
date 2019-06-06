package org.godseop.apple.exception;

import org.godseop.apple.model.Error;

public class AppleException extends RuntimeException {

    private String code;

    @Deprecated
    public AppleException(int code, String message) {
        super(message);
        this.code = String.format("%04d", code);
    }

    public AppleException(Error error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    public String getCode() {
        return this.code;
    }

}
