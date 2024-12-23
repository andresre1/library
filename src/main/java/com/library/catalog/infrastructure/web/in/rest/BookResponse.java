package com.library.catalog.infrastructure.web.in.rest;

import com.library.catalog.domain.Book;

public record BookResponse(String id, String title, String isbn) {

  public static BookResponse from(Book book) {
    return new BookResponse(book.bookId().toString(), book.title(), book.isbn().toString());
  }
}
