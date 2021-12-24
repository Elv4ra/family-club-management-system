package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.exception.*;
import edu.kpi.iasa.mmsa.club.repository.model.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class HandlerController {

    private final MessageSource messageSource;

    public HandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({FinancesNotFoundException.class, MemberRoleNotFoundException.class, RequestNotFoundException.class, EventNotFoundException.class, RoleNotFoundException.class, RankNotFoundException.class, MemberNotFoundException.class})
    public ResponseEntity<Void> handleUserNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(value = { RoleAlreadyExistsException.class })
    protected ResponseEntity<Error> handleConflict(RoleAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Role With This Name Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = { RankAlreadyExistsException.class })
    protected ResponseEntity<Error> handleConflict(RankAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Rank With This Name Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = { MemberAlreadyExistsException.class })
    protected ResponseEntity<Error> handleConflict(MemberAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Member With This Login Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = { SQLIntegrityConstraintViolationException.class })
    protected ResponseEntity<Error> handleConflict(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Argument Not Exists In Data Base").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = { EventAlreadyExistsException.class })
    protected ResponseEntity<Error> handleConflict(EventAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Event With This Date Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = { TimeParseException.class })
    protected ResponseEntity<Error> handleConflict(TimeParseException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Invalid Input Data").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = { RequestAlreadyExistsException.class })
    protected ResponseEntity<Error> handleConflict(RequestAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Request With This Date Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = { InvalidEnumDataException.class })
    protected ResponseEntity<Error> handleConflict(InvalidEnumDataException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Invalid Enum Data Exception").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = { InvalidInputDataException.class })
    protected ResponseEntity<Error> handleConflict(InvalidInputDataException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Invalid Input Data Exception").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = { MemberRoleAlreadyExistsException.class })
    protected ResponseEntity<Error> handleConflict(MemberRoleAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("This Members Role Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = { FinancesAlreadyExistsException.class })
    protected ResponseEntity<Error> handleConflict(FinancesAlreadyExistsException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Member Finances Record For This Event Already Exists").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<Error> handleConflict(IllegalArgumentException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<List<Error>> validationExceptionHandler(ConstraintViolationException ex, WebRequest request) {
        log.info("ex:", ex.getConstraintViolations().toArray());
        List<Error> errors = ex.getConstraintViolations().stream().map(violation ->
                Error.builder().description(violation.getPropertyPath() + " invalid. " +
                                messageSource.getMessage(violation.getMessage(), null, request.getLocale()))
                        .code("Bad Request").build()
        ).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(value = { NullPointerException.class })
    protected ResponseEntity<Error> handleConflict(NullPointerException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description(("Required field are missing")).build();
        return ResponseEntity.badRequest().body(error);
    }
}
