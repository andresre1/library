package com.library.catalog.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class IsbnTest {

  @Test
  void shouldThrowExceptionWhenIsbnNotValid() {
    String invalidIsbn = "123-invalid-isbn";

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new Isbn(invalidIsbn));

    assertEquals("invalid isbn: " + invalidIsbn, exception.getMessage());
  }
}
