package com.library.catalog.application;

import jakarta.validation.constraints.NotNull;
import com.library.UseCase;
import com.library.catalog.domain.BarCode;
import com.library.catalog.domain.BookId;
import com.library.catalog.domain.Copy;
import com.library.catalog.domain.CopyRepository;

@UseCase
public class RegisterBookCopyUseCase {
    private final CopyRepository copyRepository;

    public RegisterBookCopyUseCase(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    public void execute(@NotNull BookId bookId, @NotNull BarCode barCode) {
        copyRepository.save(new Copy(bookId, barCode));
    }
}