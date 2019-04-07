package uk.co.genusoft.BookSys.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
@Slf4j
public class RestExceptionProcessor {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void entityNotFoundException(HttpServletResponse response, EntityNotFoundException ex) throws IOException {
        log.error("Exception entity not found: ", ex);
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, ConstraintViolationException.class})
    public void illegalArgumentViolationException(HttpServletResponse response, Exception ex) throws IOException {
        log.error("Exception illegal or inappropriate argument received: ", ex);
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(EntityExistsException.class)
    public void entityExistsException(HttpServletResponse response, EntityExistsException ex) throws IOException {
        log.error("Exception entity already exists: ", ex);
        response.sendError(HttpStatus.CONFLICT.value());
    }

}
