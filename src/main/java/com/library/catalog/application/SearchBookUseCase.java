package com.library.catalog.application;

import com.library.UseCase;
import com.library.catalog.domain.*;

@UseCase
public class SearchBookUseCase {

  private final BookRepository bookRepository;

  public SearchBookUseCase(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public BookResponse findById(BookId bookId) {
    var book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
    return BookResponse.from(book);
  }

  //    public Optional<Book> findByIsbn(Isbn isbn) {
  //        return bookRepository.findByIsbn(isbn);
  //    }
  //
  //    public List<Book> findByTitle(String title) {
  //        return bookRepository.findByTitleContainingIgnoreCase(title);
  //    }
}
