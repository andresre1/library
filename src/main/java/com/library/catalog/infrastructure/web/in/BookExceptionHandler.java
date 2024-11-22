package com.library.catalog.infrastructure.web.in;

import com.library.catalog.domain.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler {

  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }
}
