package com.mcb.datareference_service.exception;

public class ApiCallException extends RuntimeException {
    public ApiCallException(String url, String message) {
        super("Call to " + url + " failed. " + message);
    }
}
