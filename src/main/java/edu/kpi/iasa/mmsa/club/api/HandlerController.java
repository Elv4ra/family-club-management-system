package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.exception.*;
import edu.kpi.iasa.mmsa.club.repository.model.Error;
import edu.kpi.iasa.mmsa.club.repository.model.MemberRole;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;

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

    @ExceptionHandler(value
            = { RequestNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            RequestNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Request Not Found").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { RequestAlreadyExistsException.class })
    protected ResponseEntity<Error> handleConflict(
            RequestAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Request With This Date Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { InvalidEnumDataException.class })
    protected ResponseEntity<Error> handleConflict(
            InvalidEnumDataException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Invalid Enum Data Exception").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { InvalidInputDataException.class })
    protected ResponseEntity<Error> handleConflict(
            InvalidInputDataException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Invalid Input Data Exception").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = {MemberRoleNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            MemberRoleNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Members Role Not Found").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { MemberRoleAlreadyExistsException.class })
    protected ResponseEntity<Error> handleConflict(
            MemberRoleAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("This Members Role Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }
}
