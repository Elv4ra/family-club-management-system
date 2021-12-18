package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.exception.RankAlreadyExistsException;
import edu.kpi.iasa.mmsa.club.exception.RankNotFoundException;
import edu.kpi.iasa.mmsa.club.exception.RoleAlreadyExistsException;
import edu.kpi.iasa.mmsa.club.exception.RoleNotFoundException;
import edu.kpi.iasa.mmsa.club.repository.model.Error;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class HandlerController {

    private final MessageSource messageSource;

    public HandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value
            = { RoleNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            RoleNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Role Not Found").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { RoleAlreadyExistsException.class })
    protected ResponseEntity<Error> handleConflict(
            RoleAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("This Role Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { RankNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            RankNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Rank Not Found").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { RankAlreadyExistsException.class })
    protected ResponseEntity<Error> handleConflict(
            RankAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("This Rank Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }
}
