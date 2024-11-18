package com.library.catalog.infrastructure.persistence;

import com.library.catalog.domain.Book;
import com.library.catalog.domain.BookId;
import com.library.catalog.domain.Isbn;
import org.springframework.stereotype.Component;

@Component
public class BookEntityMapper {

  public BookEntity toEntity(Book book) {
    return new BookEntity(book.bookId().id(), book.title(), book.isbn().toString());
  }

  public Book toDomain(BookEntity bookEntity) {
    return new Book(
        new BookId(bookEntity.getId()), bookEntity.getTitle(), new Isbn(bookEntity.getIsbn()));
  }
}
