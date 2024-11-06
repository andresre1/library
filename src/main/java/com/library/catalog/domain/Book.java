package com.library.catalog.domain;

import org.springframework.util.Assert;

public record Book(BookId bookId, String title, Isbn isbn) {

  public Book(String title, Isbn isbn) {
    this(new BookId(), title, isbn);
    Assert.notNull(title, "title must not be null");
    Assert.notNull(isbn, "isbn must not be null");
  }
}
