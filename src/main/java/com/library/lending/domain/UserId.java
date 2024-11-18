package com.library.lending.domain;

import java.util.UUID;
import org.springframework.util.Assert;

public record UserId(UUID id) {

  public UserId {
    Assert.notNull(id, "id must not be null");
  }

  public UserId(String id) {
    this(UUID.fromString(id));
  }

  public UserId() {
    this(UUID.randomUUID());
  }
}
