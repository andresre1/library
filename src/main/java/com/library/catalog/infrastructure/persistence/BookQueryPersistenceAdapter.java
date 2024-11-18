package com.library.catalog.infrastructure.persistence;

import com.library.catalog.domain.Book;
import com.library.catalog.domain.BookId;
import com.library.catalog.domain.BookNotFoundException;
import com.library.catalog.domain.ports.BookQueryPersistencePort;
import org.springframework.stereotype.Repository;

@Repository
public class BookQueryPersistenceAdapter implements BookQueryPersistencePort {

  private final BookRepository bookRepository;
  private final BookEntityMapper bookEntityMapper;

  public BookQueryPersistenceAdapter(
      BookRepository bookRepository, BookEntityMapper bookEntityMapper) {
    this.bookRepository = bookRepository;
    this.bookEntityMapper = bookEntityMapper;
  }

  @Override
  public Book findById(BookId bookId) {
    var book =
        bookRepository.findById(bookId.id()).orElseThrow(() -> new BookNotFoundException(bookId));
    return bookEntityMapper.toDomain(book);
  }
}
