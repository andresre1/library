package com.library.catalog.application;

import com.library.catalog.domain.Copy;
import com.library.catalog.domain.ports.CopyCreatePersistencePort;
import jakarta.validation.constraints.NotNull;
import com.library.UseCase;
import com.library.catalog.domain.BarCode;
import com.library.catalog.domain.BookId;

@UseCase
public class RegisterBookCopyUseCase {
  private final CopyCreatePersistencePort copyCreatePersistencePort;

  public RegisterBookCopyUseCase(CopyCreatePersistencePort copyCreatePersistencePort) {
    this.copyCreatePersistencePort = copyCreatePersistencePort;
  }

  public void execute(@NotNull BookId bookId, @NotNull BarCode barCode) {
    copyCreatePersistencePort.create(new Copy(bookId, barCode));
  }
}
