package pl.great.waw.company.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { PeselNotFoundException.class })
    protected ResponseEntity<Object> handleConflict(
            PeselNotFoundException exception, WebRequest request) {

        return handleExceptionInternal(exception, "Nie mogę znaleźć pracownika o podanym peselu!",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value
            = { PeselAlreadyExistException.class })
    protected ResponseEntity<Object> handleConflict(
            PeselAlreadyExistException exception, WebRequest request) {
        return handleExceptionInternal(exception, "Podany pesel już istnieje!",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }


}