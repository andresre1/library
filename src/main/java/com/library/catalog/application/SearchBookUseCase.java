package com.library.catalog.application;

import com.library.UseCase;
import com.library.catalog.domain.*;
import com.library.catalog.domain.ports.BookQueryPersistencePort;

@UseCase
public class SearchBookUseCase {

  private final BookQueryPersistencePort bookQueryPersistencePort;

  public SearchBookUseCase(BookQueryPersistencePort bookQueryPersistencePort) {
      this.bookQueryPersistencePort = bookQueryPersistencePort;
  }

  public BookResponse findById(BookId bookId) {
    var book = bookQueryPersistencePort.findById(bookId);
    return BookResponse.from(book);
  }
}
