package com.library.catalog.domain;

import org.springframework.util.Assert;

public record BarCode(String code) {
  public BarCode {
    Assert.notNull(code, "barcode must not be null");
  }
}
