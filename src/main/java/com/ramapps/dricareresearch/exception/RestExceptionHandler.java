package com.ramapps.dricareresearch.exception;

import com.ramapps.dricareresearch.dto.GenericResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GenericResponse<Void>> handleInvalidTimeFormat(IllegalArgumentException ex) {
        return new ResponseEntity<>(new GenericResponse<Void>(null, ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<Void>> handleGenericException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new GenericResponse<Void>(null, "Something went wrong",
                HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ResourceNotFoundException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<GenericResponse<Void>> handleInvalidTimeFormat(Exception ex) {
        return new ResponseEntity<>(new GenericResponse<Void>(null, ex.getMessage(),
                HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }
}