package com.library.catalog.domain;

import java.util.UUID;
import org.springframework.util.Assert;

public record BookId(UUID id) {

  public BookId {
    Assert.notNull(id, "id must not be null");
  }

  public BookId(String id) {
    this(UUID.fromString(id));
  }

  public BookId() {
    this(UUID.randomUUID());
  }

  @Override
  public String toString() {
    return id.toString();
  }
}
