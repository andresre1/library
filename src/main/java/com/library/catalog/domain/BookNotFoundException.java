package com.library.catalog.domain;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(BookId bookId) {
    super("BookEntity with ID " + bookId + " not found");
  }
}
