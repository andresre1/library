package com.library.catalog.domain.ports;

import com.library.catalog.domain.Book;

public interface BookCreatePersistencePort {
  Book create(Book book);
}
