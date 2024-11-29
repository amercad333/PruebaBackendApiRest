package co.com.amrsoftware.msvc_franchise.infrastructure.controller.exception;



import co.com.amrsoftware.msvc_franchise.domain.model.error.Error;
import co.com.amrsoftware.msvc_franchise.domain.usecase.exception.ObjectNotExistingException;
import co.com.amrsoftware.msvc_franchise.domain.usecase.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Map<String, String>> fieldValidate(WebExchangeBindException ex) {
        var error = new HashMap<String, String>();

        ex.getBindingResult().getFieldErrors().forEach(err -> {
            error.put(err.getField(), "The " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Error> objectNotFoundException(Exception ex) {
        var error = Error.builder()
            .error(ex.getMessage())
            .message("Record not found")
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .timestamp(LocalDateTime.now())
            .build();

        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(ObjectNotExistingException.class)
    public ResponseEntity<Error> objectNotExistingException(Exception ex) {
        var error = Error.builder()
                .error(ex.getMessage())
                .message("Duplicate")
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(error);
    }
}
