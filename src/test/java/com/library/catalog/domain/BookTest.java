package com.library.catalog.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class BookTest {

  @Test
  void shouldThrowExceptionWhenTitleIsNull() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> new Book(null, new Isbn("0-2774-5402-6")));

    assertEquals("title must not be null", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenIsbnIsNull() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new Book("test", null));

    assertEquals("isbn must not be null", exception.getMessage());
  }
}
