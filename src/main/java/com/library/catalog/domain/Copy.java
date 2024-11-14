package com.library.catalog.domain;

import org.springframework.util.Assert;

public record Copy(CopyId copyId, BookId bookId, BarCode barCode, boolean available) {

  public Copy(BookId bookId, BarCode barCode) {
    this(new CopyId(), bookId, barCode, true);
    Assert.notNull(bookId, "bookId must not be null");
    Assert.notNull(barCode, "barcode must not be null");
  }

  public Copy(Copy copy, boolean available) {
    this(copy.copyId, copy.bookId, copy.barCode, available);  // Delegación al constructor canónico
  }

  public Copy makeUnavailable() {
    return new Copy(this, false);
  }

  public Copy makeAvailable() {
    return new Copy(this, true);
  }
}
