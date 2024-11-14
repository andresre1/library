package com.library.catalog.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CopyTest {

  @Test
  void shouldThrowExceptionWhenBookIdIsNull() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new Copy(null, new BarCode("test")));

    assertEquals("bookId must not be null", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenBarcodeIsNull() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new Copy(new BookId(), null));

    assertEquals("barcode must not be null", exception.getMessage());
  }
}
