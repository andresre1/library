package com.library.catalog.domain;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(BookId bookId) {
    super("Book with ID " + bookId + " not found");
  }
}
