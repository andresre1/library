package com.library.catalog.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.library.catalog.domain.BarCode;
import com.library.catalog.domain.BookId;
import com.library.catalog.domain.Copy;
import com.library.catalog.domain.ports.CopyCreatePersistencePort;
import jakarta.validation.ConstraintViolationException;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RegisterBookCopyUseCaseTest {

  @Mock CopyCreatePersistencePort copyCreatePersistencePort;
  @InjectMocks RegisterBookCopyUseCase registerBookCopyUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldCreateBookCopySuccessfully() {
    BookId bookId = new BookId(UUID.randomUUID());
    BarCode barCode = new BarCode("1234567890123");

    registerBookCopyUseCase.execute(bookId, barCode);

    ArgumentCaptor<Copy> captor = ArgumentCaptor.forClass(Copy.class);
    verify(copyCreatePersistencePort, times(1)).create(captor.capture());

    Copy capturedCopy = captor.getValue();
    assertEquals(bookId, capturedCopy.bookId());
    assertEquals(barCode, capturedCopy.barCode());
    assertTrue(capturedCopy.available());
  }

  @Test
  void shouldThrowExceptionWhenBookIdIsNull() {
    BarCode barCode = new BarCode("1234567890123");

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          registerBookCopyUseCase.execute(null, barCode);
        });

    verifyNoInteractions(copyCreatePersistencePort);
  }

  @Test
  void shouldThrowExceptionWhenBarCodeIsNull() {
    BookId bookId = new BookId(UUID.randomUUID());

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          registerBookCopyUseCase.execute(bookId, null);
        });

    verifyNoInteractions(copyCreatePersistencePort);
  }

  @Test
  void shouldNotCreateBookCopyWhenValidationFails() {
    BookId bookId = new BookId(UUID.randomUUID());
    BarCode invalidBarCode = new BarCode(""); // Assume empty string is invalid

    doThrow(new ConstraintViolationException(null))
        .when(copyCreatePersistencePort)
        .create(any(Copy.class));

    assertThrows(
        ConstraintViolationException.class,
        () -> {
          registerBookCopyUseCase.execute(bookId, invalidBarCode);
        });

    verify(copyCreatePersistencePort, times(1)).create(any(Copy.class));
  }
}
