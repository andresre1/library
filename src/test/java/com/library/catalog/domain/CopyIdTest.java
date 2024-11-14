package com.library.catalog.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CopyIdTest {

  @Test
  void shouldThrowExceptionWhenIdIsNull() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new CopyId(null));

    assertEquals("id must not be null", exception.getMessage());
  }
}
