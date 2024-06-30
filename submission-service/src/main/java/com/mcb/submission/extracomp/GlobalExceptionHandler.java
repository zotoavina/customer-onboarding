package com.mcb.submission.extracomp;

import com.mcb.submission.dto.ResponseFormatDto;
import com.mcb.submission.exception.ApiCallException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    /**
     * Handle exception due to the failure while validating dto
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, InvalidParameterException.class,
            ConstraintViolationException.class})
    public ResponseEntity<ResponseFormatDto> handleValidationException(Exception e) {
        LOGGER.warn("Argument validation exception : due to error {}", e.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Object errorData = null;
        String errorMessage = "";
        if (e instanceof MethodArgumentNotValidException me) {
            Map<String, String> errors = new HashMap<>();
            me.getBindingResult()
                    .getFieldErrors()
                    .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            errorData = errors;
            errorMessage = "Invalid argument for the action";
        }
        if (e instanceof InvalidParameterException ie) {
            errorMessage = ie.getMessage();
        }

        if (e instanceof ConstraintViolationException ce) {
            errorMessage = ce.getMessage();
        }
        return ResponseFormatDto.buildResponse(errorData, status, errorMessage);
    }


    @ExceptionHandler(ApiCallException.class)
    public ResponseEntity<ResponseFormatDto> handleApiCallException(Exception e) {
        log.error("Api call exception {}", e.getMessage());
        return ResponseFormatDto.buildResponse(null, HttpStatus.BAD_REQUEST,
                "Error while calling third party api");
    }

}