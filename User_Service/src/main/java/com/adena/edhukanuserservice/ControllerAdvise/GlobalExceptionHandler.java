package com.adena.edhukanuserservice.ControllerAdvise;


import com.adena.edhukanuserservice.exceptions.UserAlreadyPresent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyPresent.class)
    public ResponseEntity<?> handleUserAlreadyPresentException(UserAlreadyPresent ex, WebRequest request) {
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.CONFLICT); // 409 Conflict
    }

}
