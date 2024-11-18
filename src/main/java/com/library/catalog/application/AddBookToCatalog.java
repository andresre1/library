package com.library.catalog.application;

import com.library.UseCase;
import com.library.catalog.domain.Book;
import com.library.catalog.domain.Isbn;
import com.library.catalog.domain.ports.BookCreatePersistencePort;

@UseCase
public class AddBookToCatalog {
  private final BookSearchService bookSearchService;
  private final BookCreatePersistencePort bookCreatePersistencePort;

  public AddBookToCatalog(
      BookSearchService bookSearchService, BookCreatePersistencePort bookCreatePersistencePort) {
    this.bookSearchService = bookSearchService;
    this.bookCreatePersistencePort = bookCreatePersistencePort;
  }

  public void execute(Isbn isbn) {
    BookInformation result = bookSearchService.search(isbn);
    Book book = new Book(result.title(), isbn);
    bookCreatePersistencePort.create(book);
  }
}
