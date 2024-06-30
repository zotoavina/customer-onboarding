package com.mcb.submission.exception;

public class ApiCallException extends RuntimeException {
    public ApiCallException(String url, String message) {
        super("Call to " + url + " failed. " + message);
    }
}
