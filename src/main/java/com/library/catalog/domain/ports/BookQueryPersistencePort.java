package com.library.catalog.domain.ports;

import com.library.catalog.domain.Book;
import com.library.catalog.domain.BookId;

public interface BookQueryPersistencePort {
  Book findById(BookId id);
}
