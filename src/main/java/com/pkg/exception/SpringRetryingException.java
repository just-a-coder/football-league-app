package com.pkg.exception;


import org.springframework.http.HttpStatus;

public class SpringRetryingException extends Exception {

    private static final long serialVersionUID = 5102555628497446895L;

    private String errMsg = null;

    private HttpStatus httpStatusCode;

    public SpringRetryingException() {
        super();
    }

    public SpringRetryingException(String message) {
        super(message);
        this.errMsg = message;
    }

    public SpringRetryingException(Throwable cause) {
        this(cause.getMessage(), cause);
    }

    public SpringRetryingException(String message, Throwable cause) {
        super(message, cause);
        this.errMsg = message;
    }

    public SpringRetryingException(HttpStatus statusCode) {
        this.httpStatusCode = statusCode;
    }

    @Override
    public String toString() {
        return errMsg;
    }

    @Override
    public String getMessage() {
        return errMsg;
    }

}
