package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.exception.*;
import edu.kpi.iasa.mmsa.club.repository.model.Error;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;

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
        Error error = Error.builder().code("BAD_REQUEST").description("Role With This Name Already Exists").build();
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
        Error error = Error.builder().code("BAD_REQUEST").description("Rank With This Name Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { MemberNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            MemberNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Member Not Found").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { MemberAlreadyExistsException.class })
    protected ResponseEntity<Error> handleConflict(
            MemberAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Member With This Login Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { IllegalArgumentException.class })
    protected ResponseEntity<Error> handleConflict(
            IllegalArgumentException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Illegal Arguments").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { SQLIntegrityConstraintViolationException.class })
    protected ResponseEntity<Error> handleConflict(
            SQLIntegrityConstraintViolationException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Given Options Not Exists").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { EventNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            EventNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Event Not Found").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { EventAlreadyExistsException.class })
    protected ResponseEntity<Error> handleConflict(
            EventAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Event With This Date Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { TimeParseException.class })
    protected ResponseEntity<Error> handleConflict(
            TimeParseException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Invalid Input Data").build();
        return ResponseEntity.badRequest().body(error);
    }
}
