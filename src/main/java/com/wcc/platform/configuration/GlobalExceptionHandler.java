package com.wcc.platform.configuration;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.wcc.platform.domain.exceptions.ContentNotFoundException;
import com.wcc.platform.domain.exceptions.ErrorDetails;
import com.wcc.platform.domain.exceptions.InvalidProgramTypeException;
import com.wcc.platform.domain.exceptions.PlatformInternalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/** Global controller to handle all exceptions for the API. */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /** Receive ContentNotFoundException and return {@link HttpStatus#NOT_FOUND}. */
  @ExceptionHandler(ContentNotFoundException.class)
  @ResponseStatus(NOT_FOUND)
  public ResponseEntity<ErrorDetails> handleNotFoundException(
      final ContentNotFoundException ex, final WebRequest request) {
    final var errorDetails =
        new ErrorDetails(NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, NOT_FOUND);
  }

  /** Receive PlatformInternalException and return {@link HttpStatus#INTERNAL_SERVER_ERROR}. */
  @ExceptionHandler(PlatformInternalException.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorDetails> handleInternalError(
      final PlatformInternalException ex, final WebRequest request) {
    final var errorDetails =
        new ErrorDetails(
            INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, INTERNAL_SERVER_ERROR);
  }

  /** Receive PlatformInternalException and return {@link HttpStatus#BAD_REQUEST}. */
  // TODO: Add new exception about duplicated member/email ...
  @ExceptionHandler({
    InvalidProgramTypeException.class,
  })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorDetails> handleProgramTypeError(
      final InvalidProgramTypeException ex, final WebRequest request) {
    final var errorDetails =
        new ErrorDetails(
            HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
}
