package com.kloia.atomikos.handler;


import com.kloia.atomikos.exception.NotFoundException;
import com.kloia.atomikos.exception.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handle(ConstraintViolationException exception) {
        String errorMsg = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse(exception.getMessage());
        ErrorDto apiError = new ErrorDto(new Date(), "Validation Failed", errorMsg);
        return new ResponseEntity<>(apiError, null, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorDto> handle(NotFoundException exception) {
        ErrorDto errorDto = new ErrorDto(new Date(), "Not Found", exception.getMessage());
        return new ResponseEntity<>(errorDto, null, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = SystemException.class)
    public ResponseEntity<ErrorDto> handle(SystemException exception) {
        ErrorDto errorDto = new ErrorDto(new Date(), "System Error", exception.getMessage());
        return new ResponseEntity<>(errorDto, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
