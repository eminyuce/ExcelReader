package com.excel.reader.exception;

import com.excel.reader.model.ServiceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServiceStatus> globalExceptionHandler(Exception ex, WebRequest request) {

        log.error("An unexpected exception occurred", ex);

        ServiceStatus apiError = new ServiceStatus();
        apiError.setStatus(null);
        return new ResponseEntity<>(apiError, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
