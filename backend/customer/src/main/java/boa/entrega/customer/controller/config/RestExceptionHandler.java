package boa.entrega.customer.controller.config;

import boa.entrega.customer.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<RestError> handleHttpMessageNotReadable(BusinessException exception) {
        return new ResponseEntity(
                new RestError(exception.getMessage()),
                HttpStatus.resolve(exception.getCode()));
    }

    @Data
    @AllArgsConstructor
    private class RestError {
        private String error;
    }
}
