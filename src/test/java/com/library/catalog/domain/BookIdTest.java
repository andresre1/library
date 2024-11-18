package com.library.catalog.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class BookIdTest {

  @Test
  void shouldThrowExceptionWhenIdIsNull() {
    UUID uuid = null;
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new BookId(uuid));

    assertEquals("id must not be null", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenIdIsNotValid() {
    String id = "test";
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new BookId(id));

    assertEquals("Invalid UUID string: " + id, exception.getMessage());
  }
}
