package com.library.catalog.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.library.catalog.domain.Book;
import com.library.catalog.domain.Isbn;
import com.library.catalog.domain.ports.BookCreatePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AddBookToCatalogTest {

  @Mock private BookSearchService bookSearchService;
  @Mock private BookCreatePersistencePort bookCreatePersistencePort;
  @InjectMocks private AddBookToCatalog addBookToCatalog;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldAddBookToCatalogSuccessfully() {
    Isbn isbn = new Isbn("9780134685991");
    BookInformation bookInformation = new BookInformation("Effective Java");

    when(bookSearchService.search(isbn)).thenReturn(bookInformation);

    addBookToCatalog.execute(isbn);

    ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
    verify(bookCreatePersistencePort, times(1)).create(captor.capture());

    Book capturedBook = captor.getValue();

    assertEquals(bookInformation.title(), capturedBook.title());
    assertEquals(isbn, capturedBook.isbn());
  }
}
