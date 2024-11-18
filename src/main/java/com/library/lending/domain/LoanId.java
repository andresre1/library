package com.library.lending.domain;

import java.util.UUID;
import org.springframework.util.Assert;

public record LoanId(UUID id) {

  public LoanId {
    Assert.notNull(id, "id must not be null");
  }

  public LoanId(String id) {
    this(UUID.fromString(id));
  }

  public LoanId() {
    this(UUID.randomUUID());
  }
}
