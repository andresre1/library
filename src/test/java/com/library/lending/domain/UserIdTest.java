package com.library.lending.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserIdTest {

  @Test
  void shouldThrowExceptionWhenIdIsNull() {
    UUID uuid = null;
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new UserId(uuid));

    assertEquals("id must not be null", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenIdIsNotValid() {
    String id = "test";
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new UserId(id));

    assertEquals("Invalid UUID string: " + id, exception.getMessage());
  }
}
