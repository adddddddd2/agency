package ru.mirea.adel.agency.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UsernameAlreadyTakenException.class})
    protected ResponseEntity<ErrorMessage> handleBadRequest(UsernameAlreadyTakenException e, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                new Date(),
                request.getRequestURI());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {WrongPropertyTypeException.class})
    protected ResponseEntity<ErrorMessage> handleBadRequest(WrongPropertyTypeException e, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                new Date(),
                request.getRequestURI());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PropertyNotExistsException.class, RoleNotFoundException.class,
            UsernameNotFoundException.class, UsernameNotFoundException.class})
    protected ResponseEntity<ErrorMessage> handleBadRequest(RuntimeException e, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                new Date(),
                request.getRequestURI());

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
