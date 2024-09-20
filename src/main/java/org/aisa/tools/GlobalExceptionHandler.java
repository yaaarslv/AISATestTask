package org.aisa.tools;

import org.aisa.models.CustomErrorResponse;
import org.aisa.tools.exceptions.CoffeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CoffeeException.class)
    public ResponseEntity<CustomErrorResponse> handleRuntimeException(Exception ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                ex.getClass().getSimpleName(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
