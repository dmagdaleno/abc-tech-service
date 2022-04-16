package br.com.fiap.abctechservice.handler;

import br.com.fiap.abctechservice.handler.exception.MaxAssistsException;
import br.com.fiap.abctechservice.handler.exception.MinimumAssistsRequiredException;
import br.com.fiap.abctechservice.handler.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MinimumAssistsRequiredException.class)
    public ResponseEntity<ErrorMessageResponse> errorMinAssistRequired(MinimumAssistsRequiredException ex){
        ErrorMessageResponse error = new ErrorMessageResponse(
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            ex.getMessage(),
            ex.getDescription()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxAssistsException.class)
    public ResponseEntity<ErrorMessageResponse> errorMaxAssisException(MaxAssistsException ex){
        ErrorMessageResponse error = new ErrorMessageResponse(
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            ex.getMessage(),
            ex.getDescription()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageResponse> validationErrorHandler(MethodArgumentNotValidException ex){
        StringBuilder description = new StringBuilder();
        ex.getBindingResult()
            .getFieldErrors()
            .forEach(fieldError -> description.append(formatErrorMessage(fieldError)));

        ErrorMessageResponse error = new ErrorMessageResponse(
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            "Invalid request",
            description.toString()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> errorOrderNotFound(OrderNotFoundException ex){
        ErrorMessageResponse error = new ErrorMessageResponse(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            ex.getDescription()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    private String formatErrorMessage(FieldError fieldError) {
        return String.format("%s - %s; ", fieldError.getField(), fieldError.getDefaultMessage());
    }
}