package com.selendroid.qa.exception;

/**
 * @author sanjay
 *
 */
public class FrameworkException extends RuntimeException {

    private static final long serialVersionUID = -5655078305898588544L;

    public FrameworkException() {
        super();
    }

    public FrameworkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrameworkException(String message) {
        super(message);
    }

    public FrameworkException(Throwable cause) {
        super(cause);
    }

}
