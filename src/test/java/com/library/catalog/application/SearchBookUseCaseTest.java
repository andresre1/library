package com.library.catalog.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.library.catalog.domain.Book;
import com.library.catalog.domain.BookId;
import com.library.catalog.domain.BookNotFoundException;
import com.library.catalog.domain.Isbn;
import com.library.catalog.domain.ports.BookQueryPersistencePort;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SearchBookUseCaseTest {

  @Mock BookQueryPersistencePort bookQueryPersistencePort;
  @InjectMocks private SearchBookUseCase searchBookUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldFindBookById() {
    UUID bookIdUuid = UUID.randomUUID();
    BookId bookId = new BookId(bookIdUuid);
    Book expectedBook = new Book("Effective Java", new Isbn("9780134685991"));

    when(bookQueryPersistencePort.findById(bookId)).thenReturn(expectedBook);

    Book result = searchBookUseCase.findById(bookId);

    assertEquals(expectedBook, result);
    verify(bookQueryPersistencePort, times(1)).findById(bookId);
  }

  @Test
  void shouldThrowExceptionWhenBookNotFound() {
    UUID bookIdUuid = UUID.randomUUID();
    BookId bookId = new BookId(bookIdUuid);

    when(bookQueryPersistencePort.findById(bookId)).thenThrow(new BookNotFoundException(bookId));

    BookNotFoundException exception =
        Assertions.assertThrows(
            BookNotFoundException.class, () -> searchBookUseCase.findById(bookId));

    assertEquals("BookEntity with ID " + bookId.id() + " not found", exception.getMessage());
    verify(bookQueryPersistencePort, times(1)).findById(bookId);
  }
}
