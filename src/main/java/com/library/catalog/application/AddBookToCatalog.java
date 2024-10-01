package com.library.catalog.application;

import com.library.UseCase;
import com.library.catalog.domain.Book;
import com.library.catalog.domain.BookRepository;
import com.library.catalog.domain.Isbn;

@UseCase
public class AddBookToCatalog {
  private final BookSearchService bookSearchService;
  private final BookRepository bookRepository;

  public AddBookToCatalog(
      BookSearchService bookSearchService, BookRepository bookRepository) {
    this.bookSearchService = bookSearchService;
    this.bookRepository = bookRepository;
  }

  public void execute(Isbn isbn) {
    BookInformation result = bookSearchService.search(isbn);
    Book book = new Book(result.title(), isbn);
    bookRepository.save(book);
  }
}
